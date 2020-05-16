package com.example.quartzservice;

import com.example.quartzservice.config.MqConfig;
import com.example.quartzservice.constants.JobDataConstants;
import com.example.quartzservice.dao.TaskInfoDao;
import com.example.quartzservice.entity.TaskInfoEntity;
import com.example.quartzservice.enums.JobStatusEnum;
import com.example.quartzservice.job.CommonJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuartzSchedulerManager {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TaskInfoDao taskInfoDao;


    public void initScheduler() throws SchedulerException {
        List<TaskInfoEntity> taskList = taskInfoDao.selectByJobStatus(JobStatusEnum.UNFROZEN.getCode());
        for (TaskInfoEntity task : taskList) {
            addJob(task);
        }
    }

    /**
     * @Description: 添加一个定时任务
     */
    public void addJob(TaskInfoEntity entity) throws SchedulerException {
        String name = entity.getJobName();
        String group = entity.getJobGroup();
        // 任务名，任务组，任务执行类
        JobDetail jobDetail= JobBuilder.newJob(CommonJob.class)
                .withIdentity(name, group)
                .usingJobData(JobDataConstants.JOB_ID, entity.getId())
                .usingJobData(JobDataConstants.QUEUE_NAME, MqConfig.map.get(entity.getJobGroup()))
                .usingJobData(JobDataConstants.JOB_EXECUTOR, entity.getJobExecutor())
                .build();
        // 触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(entity.getCronExpression()))
                .startNow()
                .build();
        // 调度容器设置JobDetail和Trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * @Description: 修改一个任务的触发时间
     */
    public void modifyJobTime(TaskInfoEntity entity) throws SchedulerException {
            TriggerKey triggerKey = TriggerKey.triggerKey(entity.getJobName(), entity.getJobGroup());
            CronTrigger oldTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (oldTrigger == null) {
                return;
            }
            String oldCron = oldTrigger.getCronExpression();
            if (!oldCron.equalsIgnoreCase(entity.getCronExpression())) {
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(entity.getCronExpression()))
                        .startNow()
                        .build();
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
    }

    /**
     * @Description: 移除一个任务
     */
    public void removeJob(TaskInfoEntity entity) throws SchedulerException {
            TriggerKey triggerKey = TriggerKey.triggerKey(entity.getJobName(), entity.getJobGroup());
            JobKey jobKey = JobKey.jobKey(entity.getJobName(), entity.getJobGroup());
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(jobKey);// 删除任务
    }

    /**
     * @Description: 暂停一个任务
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        scheduler.pauseJob(jobKey);
    }



    /**
     * @Description: 恢复一个任务(使用默认组名)
     * @param jobName
     */
    public void resumeJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        scheduler.resumeJob(jobKey);
    }

    /**
     * @Description: 启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}

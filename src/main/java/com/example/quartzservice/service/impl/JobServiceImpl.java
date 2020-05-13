package com.example.quartzservice.service.impl;

import com.example.quartzservice.constants.JobDataConstants;
import com.example.quartzservice.dao.TaskInfoDao;
import com.example.quartzservice.entity.TaskInfoEntity;
import com.example.quartzservice.enums.JobStatusEnum;
import com.example.quartzservice.job.CommonJob;
import com.example.quartzservice.request.AddJobRequest;
import com.example.quartzservice.request.DeleteJobRequest;
import com.example.quartzservice.request.UpdateJobCronRequest;
import com.example.quartzservice.service.JobService;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private SchedulerFactory schedulerFactory;

    @Autowired
    private TaskInfoDao taskInfoDao;

    @Override
    @SuppressWarnings("unchecked")
    public Integer addJob(AddJobRequest request) throws SchedulerException {
//        TaskInfoEntity entity = new TaskInfoEntity();
//        BeanUtils.copyProperties(request, entity);
//        entity.setJobStatus(JobStatusEnum.UNFROZEN.getCode());
//        int id = taskInfoDao.insert(entity);
//        Scheduler scheduler = schedulerFactory.getScheduler(entity.getJobGroup());
//        scheduler(entity, scheduler);
//        QuartzSchedulerManager.addJob(entity);
        return 314;
    }

    private void scheduler(TaskInfoEntity entity, Scheduler scheduler) throws SchedulerException {
        String jobName = entity.getJobName();
        String groupName = entity.getJobGroup();
        JobDetail jobDetail = JobBuilder
                .newJob(CommonJob.class)
                .withIdentity(jobName, groupName)
                .usingJobData(JobDataConstants.JOB_NAME, jobName)
                .usingJobData(JobDataConstants.GROUP_NAME, groupName)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, groupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(entity.getCronExpression()))
                .startNow()
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public Boolean deleteJob(DeleteJobRequest request) throws SchedulerException {
        TaskInfoEntity entity = new TaskInfoEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setJobStatus(JobStatusEnum.FROZEN.getCode());
        Scheduler scheduler = schedulerFactory.getScheduler();
        return null;
    }

    @Override
    public Boolean updateJobCron(UpdateJobCronRequest request) {
        return null;
    }

    @Override
    public Boolean startJobs() {
        return null;
    }

    @Override
    public Boolean shutdownJobs() {
        return null;
    }

    @Override
    public Boolean startJob() {
        return null;
    }
}

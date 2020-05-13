package com.example.quartzservice.entity;

import java.util.Date;

public class TaskInfoEntity {
    private Integer id;

    private String jobName;

    private String jobGroup;

    private String cronExpression;

    private Integer jobStatus;

    private String jobExecutor;

    private String description;

    private Date createTime;

    private Date updateTime;

    public TaskInfoEntity(Integer id, String jobName, String jobGroup, String cronExpression, Integer jobStatus, String jobExecutor, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.cronExpression = cronExpression;
        this.jobStatus = jobStatus;
        this.jobExecutor = jobExecutor;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TaskInfoEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobExecutor() {
        return jobExecutor;
    }

    public void setJobExecutor(String jobExecutor) {
        this.jobExecutor = jobExecutor == null ? null : jobExecutor.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
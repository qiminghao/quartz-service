package com.example.quartzservice.service;

import com.example.quartzservice.request.AddJobRequest;
import com.example.quartzservice.request.DeleteJobRequest;
import com.example.quartzservice.request.UpdateJobCronRequest;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

public interface JobService {

    public Integer addJob(AddJobRequest request) throws SchedulerException;

    public Boolean deleteJob(DeleteJobRequest request) throws SchedulerException;

    public Boolean updateJobCron(UpdateJobCronRequest request);

    public Boolean startJobs();

    public Boolean shutdownJobs();

    public Boolean startJob();
}

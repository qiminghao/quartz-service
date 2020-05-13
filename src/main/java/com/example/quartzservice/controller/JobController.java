package com.example.quartzservice.controller;

import com.example.quartzservice.enums.ResponseEnum;
import com.example.quartzservice.request.AddJobRequest;
import com.example.quartzservice.request.DeleteJobRequest;
import com.example.quartzservice.response.BaseResponse;
import com.example.quartzservice.service.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("/add")
    public BaseResponse addJob(@RequestBody AddJobRequest request) throws SchedulerException {
        return BaseResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(jobService.addJob(request))
                .build();
    }

    @RequestMapping("/delete")
    public BaseResponse deleteJob(@RequestBody DeleteJobRequest request) throws SchedulerException {
        return BaseResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(jobService.deleteJob(request))
                .build();
    }
}

package com.example.quartzservice.request;

import com.example.quartzservice.entity.JobExecutor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddJobRequest {

    @NotBlank
    private String jobName;

    @NotBlank
    private String jobGroup;

    @NotBlank
    private String cronExpression;

    @NotBlank
    private String description;

    @NotBlank
    private JobExecutor jobExecutor;
}

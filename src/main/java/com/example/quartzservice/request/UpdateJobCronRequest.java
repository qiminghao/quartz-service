package com.example.quartzservice.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateJobCronRequest {

    @NotBlank
    private String jobName;

    @NotBlank
    private String groupName;

    @NotBlank
    private String cron;
}

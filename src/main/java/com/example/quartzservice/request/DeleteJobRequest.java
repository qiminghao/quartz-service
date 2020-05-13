package com.example.quartzservice.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteJobRequest {

    @NotBlank
    private String jobName;

    @NotBlank
    private String groupName;
}

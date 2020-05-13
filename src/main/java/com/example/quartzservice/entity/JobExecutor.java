package com.example.quartzservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class JobExecutor {

    private String serviceName;

    private String className;

    private Map<String, String> params;
}

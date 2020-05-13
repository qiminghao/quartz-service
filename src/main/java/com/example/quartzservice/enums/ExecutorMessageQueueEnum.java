package com.example.quartzservice.enums;

public enum ExecutorMessageQueueEnum {

    BUSINESS_SERVICE_ONE("business_service_one", "business_service_one_queue_name"),

    BUSINESS_SERVICE_TWO("business_service_two", "business_service_two_queue_name"),

    BUSINESS_SERVICE_THREE("business_service_three", "business_service_three_queue_name");

    private String executor;

    private String queueName;

    ExecutorMessageQueueEnum(String executor, String queueName) {
        this.executor = executor;
        this.queueName = queueName;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}

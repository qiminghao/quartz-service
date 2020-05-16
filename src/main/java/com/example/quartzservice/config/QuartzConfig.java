package com.example.quartzservice.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean(name="schedulerFactory")
    public SchedulerFactory schedulerFactory() {
        return new StdSchedulerFactory();
    }

    @Bean(name="scheduler")
    public Scheduler scheduler(SchedulerFactory schedulerFactory) throws SchedulerException {
        return schedulerFactory.getScheduler();
    }
}

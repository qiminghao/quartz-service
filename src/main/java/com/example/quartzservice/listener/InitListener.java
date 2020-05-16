package com.example.quartzservice.listener;

import com.example.quartzservice.QuartzSchedulerManager;
import com.example.quartzservice.config.MqConfig;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitListener implements ApplicationListener<ContextRefreshedEvent> {

    private static boolean isInitialized = false;

    @Autowired
    private QuartzSchedulerManager manager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!isInitialized) {
            MqConfig.init();
            try {
                manager.initScheduler();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}

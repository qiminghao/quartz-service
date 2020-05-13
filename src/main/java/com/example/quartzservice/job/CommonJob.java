package com.example.quartzservice.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.example.quartzservice.constants.JobDataConstants;
import com.example.quartzservice.entity.JobExecutor;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Slf4j
public class CommonJob implements Job {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String queueName = jobDataMap.getString(JobDataConstants.QUEUE_NAME);
        String jobExecutor = jobDataMap.getString(JobDataConstants.JOB_EXECUTOR);
        String id = jobDataMap.getString(JobDataConstants.JOB_ID);
        String message = JSON.toJSONString(new JobExecutor(id, jobExecutor, Maps.newHashMap()));
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}

package com.example.quartzservice.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SendScheduleMessageTask {

    @Autowired
    private JmsTemplate jmsTemplate;

//    @Scheduled(cron="0 0 10 * * ?")
//    @Scheduled(cron = "*/5 * * * * ?")
    public void handle() {
        String message = "{\"id\": 314,\"jobExecutor\": {\"serviceName\": \"CommonService\",\"className\": \"com.ss.nds.message.MessageGenerator\",\"methodName\": \"process\",\"params\": {\"messageId\": \"3623\"}}}";
        jmsTemplate.send("scheduler_execute_commonservice", session -> session.createTextMessage(message));
        System.out.println("调度消息发送成功，查看scheduler_execute_commonservice");
    }

    @JmsListener(destination = "scheduler_execute_commonservice", containerFactory = "jmsListenerContainerFactory")
    public void handler1() {
        String message = "{\"id\":123, \"success\":true, \"time\":" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "}";
        jmsTemplate.send("scheduler_result_commonservice", session -> session.createTextMessage(message));
        System.out.println("调度结果发送成功，查看scheduler_result_commonservice");
    }

//    @JmsListener(destination = "scheduler_result_commonservice", containerFactory = "jmsListenerContainerFactory")
    public void handler2() {
//        String message = "{\"id\":123, \"success\":true, \"time\":" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "}";
//        jmsTemplate.send("scheduler_result_commonservice", session -> session.createTextMessage(message));
    }
}

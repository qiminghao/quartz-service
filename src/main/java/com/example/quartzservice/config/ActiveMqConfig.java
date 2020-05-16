package com.example.quartzservice.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ActiveMqConfig {

    @Bean(name="activeMQConnectionFactory")
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean(name="connectionFactory")
    public CachingConnectionFactory connectionFactory(ConnectionFactory activeMQConnectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
        cachingConnectionFactory.setSessionCacheSize(100);
        return cachingConnectionFactory;
    }

    @Bean(name="jmsTemplate")
    public JmsTemplate jmsTemplate(ConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory);
        jmsTemplate.setPubSubDomain(false);
        return jmsTemplate;
    }

    @Bean(name="jmsListenerContainerFactory")
    public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}

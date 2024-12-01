package com.bank.swift_gateway.config;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQQueueManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${ibm.mq.queueManager}")
    private String queueManagerName;

    @Value("${ibm.mq.host}")
    private String host;

    @Value("${ibm.mq.port}")
    private int port;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Bean
    public MQQueueManager mqQueueManager() throws Exception {
        MQEnvironment.hostname = host;
        MQEnvironment.port = port;
        MQEnvironment.channel = channel;
        return new MQQueueManager(queueManagerName);
    }
}

package com.bank.swift_gateway.listener;

import com.bank.swift_gateway.service.SwiftPaymentMessageService;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQGetMessageOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MQListener {

    private static final Logger logger = LoggerFactory.getLogger(MQListener.class);

    @Value("${ibm.mq.queueName}")
    private String queueName;

    @Autowired
    private MQQueueManager queueManager;

    @Autowired
    private SwiftPaymentMessageService messageService;

    @PostConstruct
    public void startListening() {
        new Thread(this::listen).start();
    }

    private void listen() {
        MQQueue queue = null;
        try {
            queue = accessQueue();
            MQGetMessageOptions gmo = createGetMessageOptions();

            while (true) {
                MQMessage message = new MQMessage();
                queue.get(message, gmo);
                String msgText = message.readStringOfByteLength(message.getMessageLength());
                logger.info("Received message: {}", msgText);
                messageService.saveMessage(msgText);
            }
        } catch (MQException | java.io.IOException e) {
            logger.error("Error while listening to the queue", e);
        } finally {
            closeQueue(queue);
            disconnectQueueManager();
        }
    }

    private MQQueue accessQueue() throws MQException {
        int openOptions = CMQC.MQOO_INPUT_AS_Q_DEF | CMQC.MQOO_FAIL_IF_QUIESCING;
        return queueManager.accessQueue(queueName, openOptions);
    }

    private MQGetMessageOptions createGetMessageOptions() {
        MQGetMessageOptions gmo = new MQGetMessageOptions();
        gmo.options = CMQC.MQGMO_WAIT | CMQC.MQGMO_FAIL_IF_QUIESCING;
        gmo.waitInterval = CMQC.MQWI_UNLIMITED;
        return gmo;
    }

    private void closeQueue(MQQueue queue) {
        if (queue != null) {
            try {
                queue.close();
            } catch (MQException e) {
                logger.error("Error while closing the queue", e);
            }
        }
    }

    private void disconnectQueueManager() {
        if (queueManager != null) {
            try {
                queueManager.disconnect();
            } catch (MQException e) {
                logger.error("Error while disconnecting the queue manager", e);
            }
        }
    }
}
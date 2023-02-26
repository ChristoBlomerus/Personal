package com.christo.rabbitMQ;

import com.christo.database.entity.EventAudit;
import com.christo.database.service.EventAuditService;
import com.christo.rabbitMQ.object.EventAuditQueueObj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author christo
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RabbitMQService {
    @Autowired 
    private EventAuditService eventAuditService;
    
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQService.class);
    @Value("${rabbitMQ.queueName}")
    private String QUEUE_NAME = "";
    @Value("${rabbitMQ.queueHost}")
    private String QUEUE_HOST = "";
    
    public void start (){
        logger.info("RabbitMQService startup, QUEUE_NAME = "+ QUEUE_NAME+ "QUEUE_HOST = " + QUEUE_HOST);
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(QUEUE_HOST);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String requestId = UUID.randomUUID().toString().replace("-", "");
                Gson gson = new GsonBuilder().create();
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                logger.info("[" + requestId + "] Received '" + message + "'");
                EventAuditQueueObj eventAuditQueueObj = gson.fromJson(message, EventAuditQueueObj.class);
                EventAudit eventAudit = new EventAudit();
                eventAudit.setRequestId(requestId);
                eventAudit.setEventName(eventAuditQueueObj.getEventName());
                eventAudit.setEventBody(gson.toJson(eventAuditQueueObj.getBody()));
                eventAudit = eventAuditService.saveEventAudit(eventAudit);
                logger.info("EventAudit[" + requestId + "] Saved to DB");
                logger.info(eventAudit.toString());

            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    public boolean sendToQueue(Object body, String eventName) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(QUEUE_HOST);
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            EventAuditQueueObj eventAuditQueueObj = new EventAuditQueueObj();
            eventAuditQueueObj.setBody(body);
            eventAuditQueueObj.setEventName(eventName);
            Gson gson = new GsonBuilder().create();
            String jsonStr = gson.toJson(eventAuditQueueObj);
            channel.basicPublish("", QUEUE_NAME, null, jsonStr.getBytes(StandardCharsets.UTF_8));
            logger.info("Sent '" + jsonStr + "' to queue");
            return true;
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }
}

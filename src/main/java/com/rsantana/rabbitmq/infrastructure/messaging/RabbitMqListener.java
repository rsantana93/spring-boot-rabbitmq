package com.rsantana.rabbitmq.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsantana.rabbitmq.config.RabbitMQConfig;
import com.rsantana.rabbitmq.domain.model.Event;
import com.rsantana.rabbitmq.domain.service.EventProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import java.time.LocalDateTime;

@Component
public class RabbitMqListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

    private final EventProcessingService eventProcessingService;

    @Autowired
    public RabbitMqListener(EventProcessingService eventProcessingService) {
        this.eventProcessingService = eventProcessingService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            logger.info("Received message: {}", message);

            // Converter a mensagem recebida em um objeto Event
            Event event = new Event("defaultType", message, LocalDateTime.now());

            // Processar o evento
            eventProcessingService.processEvent(event);

            // Confirmar a mensagem como processada
            channel.basicAck(tag, false);
            logger.info("Message processed and acknowledged: {}", message);
        } catch (Exception e) {
            logger.error("Failed to process message: {}", message, e);
            try {
                channel.basicNack(tag, false, true); // Requeue the message for retry
            } catch (Exception nackEx) {
                logger.error("Failed to nack message", nackEx);
            }
        }
    }
}
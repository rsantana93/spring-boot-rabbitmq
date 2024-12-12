package com.rsantana.rabbitmq.application;

import com.rsantana.rabbitmq.config.RabbitMQConfig;
import com.rsantana.rabbitmq.domain.model.Event;
import com.rsantana.rabbitmq.domain.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EventService(EventRepository eventRepository, RabbitTemplate rabbitTemplate) {
        this.eventRepository = eventRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Event> getAllEvents() {
        logger.info("Fetching all events from the database");
        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
        logger.info("Saving event of type: {}", event.getEventType());
        try {
            Event savedEvent = eventRepository.save(event);
            logger.info("Event saved successfully with ID: {}", savedEvent.getId());
            return savedEvent;
        } catch (Exception e) {
            logger.error("Error saving event: {}", event, e);
            throw e; // Propagate the exception for higher-level handling if necessary
        }
    }

    public Event createEvent(Event event) {
        // Publica o evento no RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, event.getEventData());

        return event;
    }
}

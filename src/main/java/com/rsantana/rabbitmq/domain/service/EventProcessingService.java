package com.rsantana.rabbitmq.domain.service;

import com.rsantana.rabbitmq.domain.model.Event;
import com.rsantana.rabbitmq.domain.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(EventProcessingService.class);

    private final EventRepository eventRepository;

    @Autowired
    public EventProcessingService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void processEvent(Event event) {
        logger.info("Processing event with type: {}", event.getEventType());
        try {
            eventRepository.save(event);
            logger.info("Event processed successfully: {}", event.getId());
        } catch (Exception e) {
            logger.error("Error processing event: {}", event, e);
            throw e;  // rethrow the exception if necessary
        }
    }
}
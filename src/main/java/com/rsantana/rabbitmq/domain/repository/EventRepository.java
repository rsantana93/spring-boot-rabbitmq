package com.rsantana.rabbitmq.domain.repository;

import com.rsantana.rabbitmq.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
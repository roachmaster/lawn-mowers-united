package com.lessonsbyleo.LawnMowersUnited.event.publisher;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;

public interface EventPublisher {
    void publish(LawnMowersUnitedEvent event);
}

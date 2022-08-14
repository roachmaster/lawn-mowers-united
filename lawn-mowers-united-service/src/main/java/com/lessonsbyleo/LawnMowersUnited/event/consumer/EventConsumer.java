package com.lessonsbyleo.LawnMowersUnited.event.consumer;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;

public interface EventConsumer {
    void consume(LawnMowersUnitedEvent lawnMowersUnitedEvent);
}

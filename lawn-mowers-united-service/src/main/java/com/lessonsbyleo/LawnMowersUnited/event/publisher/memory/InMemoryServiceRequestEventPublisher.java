package com.lessonsbyleo.LawnMowersUnited.event.publisher.memory;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.event.consumer.memory.InMemoryServiceRequestEventConsumer;
import com.lessonsbyleo.LawnMowersUnited.event.profile.InMemoryCondition;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.LawnMowerCustomerServiceRequestEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component("inMemoryServiceRequestEventPublisher")
@Conditional(InMemoryCondition.class)
public class InMemoryServiceRequestEventPublisher implements LawnMowerCustomerServiceRequestEventPublisher {
    @Autowired
    private InMemoryServiceRequestEventConsumer inMemoryServiceRequestEventConsumer;
    @Override
    public void publish(LawnMowersUnitedEvent serviceRequestEvent) {
        CompletableFuture.runAsync(() -> inMemoryServiceRequestEventConsumer.consume(serviceRequestEvent));
    }
}

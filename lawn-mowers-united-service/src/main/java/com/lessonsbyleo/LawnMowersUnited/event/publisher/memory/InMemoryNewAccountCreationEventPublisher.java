package com.lessonsbyleo.LawnMowersUnited.event.publisher.memory;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.event.consumer.memory.InMemoryNewAccountCreationEventConsumer;
import com.lessonsbyleo.LawnMowersUnited.event.profile.InMemoryCondition;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.NewAccountCreationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component("inMemoryNewAccountCreationEventPublisher")
@Conditional(InMemoryCondition.class)
public class InMemoryNewAccountCreationEventPublisher implements NewAccountCreationEventPublisher {
    @Autowired
    private InMemoryNewAccountCreationEventConsumer inMemoryNewAccountCreationEventConsumer;
    @Override
    public void publish(LawnMowersUnitedEvent newAccountCreationEvent) {
        CompletableFuture.runAsync(() -> inMemoryNewAccountCreationEventConsumer.consume(newAccountCreationEvent));
    }
}

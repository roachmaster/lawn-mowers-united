package com.lessonsbyleo.LawnMowersUnited.event.publisher.factory;

import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.LawnMowerCustomerServiceRequestEventPublisher;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.NewAccountCreationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherFactory {

    private final NewAccountCreationEventPublisher inMemoryNewAccountCreationEventPublisher;
    private final LawnMowerCustomerServiceRequestEventPublisher inMemoryServiceRequestEventPublisher;

    @Autowired
    public EventPublisherFactory(NewAccountCreationEventPublisher inMemoryNewAccountCreationEventPublisher,
                                 LawnMowerCustomerServiceRequestEventPublisher inMemoryServiceRequestEventPublisher) {
        this.inMemoryNewAccountCreationEventPublisher = inMemoryNewAccountCreationEventPublisher;
        this.inMemoryServiceRequestEventPublisher = inMemoryServiceRequestEventPublisher;
    }

    public LawnMowerCustomerServiceRequestEventPublisher createServiceRequestEventPublisher(){
        return inMemoryServiceRequestEventPublisher;
    }

    public NewAccountCreationEventPublisher createNewAccountCreationEventPublisher(){
        return inMemoryNewAccountCreationEventPublisher;
    }
}

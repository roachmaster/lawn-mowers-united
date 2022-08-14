package com.lessonsbyleo.LawnMowersUnited.event.consumer.memory;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.event.consumer.LawnMowerCustomerServiceRequestEventConsumer;
import com.lessonsbyleo.LawnMowersUnited.event.profile.InMemoryCondition;
import com.lessonsbyleo.LawnMowersUnited.service.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(InMemoryCondition.class)
public class InMemoryServiceRequestEventConsumer implements LawnMowerCustomerServiceRequestEventConsumer {
    @Autowired
    private DispatcherService dispatcherService;
    @Override
    public void consume(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        dispatcherService.dispatch(lawnMowersUnitedEvent);
    }
}

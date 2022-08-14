package com.lessonsbyleo.LawnMowersUnited.event.consumer;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.service.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestEventConsumerTestDouble implements LawnMowerCustomerServiceRequestEventConsumer {
    @Autowired
    private DispatcherService dispatcherService;
    @Override
    public void consume(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        dispatcherService.dispatch(lawnMowersUnitedEvent);
    }
}

package com.lessonsbyleo.LawnMowersUnited.event.consumer;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewAccountCreationEventConsumerTestDouble implements NewAccountCreationEventConsumer{
    @Autowired
    AccountService accountService;
    @Override
    public void consume(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        accountService.notifyLawnMowerCompany(lawnMowersUnitedEvent);
    }
}

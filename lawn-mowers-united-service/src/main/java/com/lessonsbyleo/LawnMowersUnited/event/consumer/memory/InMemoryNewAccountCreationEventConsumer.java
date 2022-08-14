package com.lessonsbyleo.LawnMowersUnited.event.consumer.memory;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.event.consumer.NewAccountCreationEventConsumer;
import com.lessonsbyleo.LawnMowersUnited.event.profile.InMemoryCondition;
import com.lessonsbyleo.LawnMowersUnited.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(InMemoryCondition.class)
public class InMemoryNewAccountCreationEventConsumer implements NewAccountCreationEventConsumer {
    @Autowired
    private AccountService accountService;
    @Override
    public void consume(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        accountService.notifyLawnMowerCompany(lawnMowersUnitedEvent);

    }
}

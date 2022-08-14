package com.lessonsbyleo.LawnMowersUnited.event;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.data.Customer;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.LawnMowerCustomerServiceRequestEventPublisher;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.NewAccountCreationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LawnMowersUnitedEventAdapter {
    private final NewAccountCreationEventPublisher newAccountCreationEventPublisher;
    private final LawnMowerCustomerServiceRequestEventPublisher lawnMowerCustomerServiceRequestEventPublisher;

    @Autowired
    public LawnMowersUnitedEventAdapter(NewAccountCreationEventPublisher newAccountCreationEventPublisher, LawnMowerCustomerServiceRequestEventPublisher lawnMowerCustomerServiceRequestEventPublisher) {
        this.newAccountCreationEventPublisher = newAccountCreationEventPublisher;
        this.lawnMowerCustomerServiceRequestEventPublisher = lawnMowerCustomerServiceRequestEventPublisher;
    }

    public void publishNewAccountCreationEventFor(LawnMowerCompany lawnMowerCompany) {
        LawnMowersUnitedEvent newAccountCreationEvent = LawnMowersUnitedEventFactory.createNewAccountCreationEventFor(lawnMowerCompany);
        newAccountCreationEventPublisher.publish(newAccountCreationEvent);
    }

    public void publishServiceRequestEvent(Customer customer, LawnMowerCompany lawnMowerCompany){
        LawnMowersUnitedEvent customerServiceRequestEvent = LawnMowersUnitedEventFactory.createCustomerServiceRequest(customer, lawnMowerCompany);
        lawnMowerCustomerServiceRequestEventPublisher.publish(customerServiceRequestEvent);
    }
}

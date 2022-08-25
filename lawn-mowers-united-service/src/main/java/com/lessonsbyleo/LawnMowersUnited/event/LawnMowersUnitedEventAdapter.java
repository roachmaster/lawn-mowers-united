package com.lessonsbyleo.LawnMowersUnited.event;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.data.Customer;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.factory.EventPublisherFactory;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.LawnMowerCustomerServiceRequestEventPublisher;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.NewAccountCreationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LawnMowersUnitedEventAdapter {
    private final EventPublisherFactory eventPublisherFactory;

    @Autowired
    public LawnMowersUnitedEventAdapter(EventPublisherFactory eventPublisherFactory) {
        this.eventPublisherFactory = eventPublisherFactory;
    }

    public void publishNewAccountCreationEventFor(LawnMowerCompany lawnMowerCompany) {
        LawnMowersUnitedEvent event = LawnMowersUnitedEventFactory.createNewAccountCreationEventFor(lawnMowerCompany);
        NewAccountCreationEventPublisher eventPublisher = eventPublisherFactory.createNewAccountCreationEventPublisher();
        eventPublisher.publish(event);
    }

    public void publishServiceRequestEvent(Customer customer, LawnMowerCompany lawnMowerCompany){
        LawnMowersUnitedEvent event = LawnMowersUnitedEventFactory.createCustomerServiceRequest(customer, lawnMowerCompany);
        LawnMowerCustomerServiceRequestEventPublisher eventPublisher = eventPublisherFactory.createServiceRequestEventPublisher();
        eventPublisher.publish(event);
    }
}

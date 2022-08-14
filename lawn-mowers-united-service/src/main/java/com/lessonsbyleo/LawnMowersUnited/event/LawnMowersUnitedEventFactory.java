package com.lessonsbyleo.LawnMowersUnited.event;

import com.lessonsbyleo.LawnMowersUnited.data.AccountType;
import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.data.Customer;

public class LawnMowersUnitedEventFactory {

    public static LawnMowersUnitedEvent createNewAccountCreationEventFor(LawnMowerCompany lawnMowerCompany) {
        LawnMowersUnitedEvent newAccountCreationEvent = new LawnMowersUnitedEvent();
        newAccountCreationEvent.setType(AccountType.LAWN_MOWER_COMPANY.name());
        newAccountCreationEvent.setEmail(lawnMowerCompany.getCompanyEmail());
        newAccountCreationEvent.setDescription("New Account Created for " + lawnMowerCompany.getCompanyName() + ":" + lawnMowerCompany.getCompanyEmail());
        return newAccountCreationEvent;
    }

    public static LawnMowersUnitedEvent createCustomerServiceRequest(Customer customer, LawnMowerCompany lawnMowerCompany) {
        LawnMowersUnitedEvent customerServiceRequestEvent = new LawnMowersUnitedEvent();
        customerServiceRequestEvent.setEmail(customer.getCustomerEmail());
        customerServiceRequestEvent.setType(AccountType.LAWN_MOWER_CUSTOMER.name());
        customerServiceRequestEvent.setDescription("Send service request to " + lawnMowerCompany.getCompanyEmail());
        return customerServiceRequestEvent;
    }
}

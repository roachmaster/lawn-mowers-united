package com.lessonsbyleo.LawnMowersUnited.db.factory;

import com.lessonsbyleo.LawnMowersUnited.data.Customer;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCustomerEntity;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCustomerKey;

public class LawnMowerCustomerEntityFactory {
    public static LawnMowerCustomerEntity create(Customer customer) {
        LawnMowerCustomerKey lawnMowerCustomerKey = new LawnMowerCustomerKey();
        lawnMowerCustomerKey.setName(customer.getCustomerName());
        lawnMowerCustomerKey.setEmail(customer.getCustomerEmail());
        return new LawnMowerCustomerEntity(lawnMowerCustomerKey);
    }
}

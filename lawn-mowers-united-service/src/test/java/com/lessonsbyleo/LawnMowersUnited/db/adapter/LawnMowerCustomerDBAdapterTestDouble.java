package com.lessonsbyleo.LawnMowersUnited.db.adapter;

import com.lessonsbyleo.LawnMowersUnited.data.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class LawnMowerCustomerDBAdapterTestDouble implements LawnMowerCustomerDBAdapterInf {
    @Autowired
    LawnMowerCustomerDBAdapter lawnMowerCustomerDBAdapter;
    @Override
    public void delete(Customer customer) {
        lawnMowerCustomerDBAdapter.delete(customer);
    }

    @Override
    public boolean doesAccountExistFor(Customer customer) {
        return lawnMowerCustomerDBAdapter.doesAccountExistFor(customer);
    }

    @Override
    public void createAccount(Customer customer) {
        lawnMowerCustomerDBAdapter.createAccount(customer);
    }
}

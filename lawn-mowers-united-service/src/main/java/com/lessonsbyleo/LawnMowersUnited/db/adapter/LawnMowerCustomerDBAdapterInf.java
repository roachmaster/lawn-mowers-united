package com.lessonsbyleo.LawnMowersUnited.db.adapter;

import com.lessonsbyleo.LawnMowersUnited.data.Customer;

public interface LawnMowerCustomerDBAdapterInf {
    void delete(Customer customer);

    boolean doesAccountExistFor(Customer customer);

    void createAccount(Customer customer);
}

package com.lessonsbyleo.LawnMowersUnited.bdd.customer;

import com.lessonsbyleo.LawnMowersUnited.data.Customer;

public class CustomerTestFactory {
    public static Customer create() {
        String name = "customerName";
        String email = "customerEmail";
        Customer customer = new Customer(name,email);
        return customer;
    }
}

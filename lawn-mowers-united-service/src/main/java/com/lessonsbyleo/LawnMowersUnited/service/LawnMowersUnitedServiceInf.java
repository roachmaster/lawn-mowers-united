package com.lessonsbyleo.LawnMowersUnited.service;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.data.Customer;
import com.lessonsbyleo.LawnMowersUnited.data.AccountCreationResponse;
import com.lessonsbyleo.LawnMowersUnited.data.ServiceResponse;

import java.util.List;

public interface LawnMowersUnitedServiceInf {
    AccountCreationResponse createAccount(LawnMowerCompany lawnMowerCompany);
    AccountCreationResponse createAccount(Customer customer);

    List<LawnMowerCompany> getLawnMowerCompanyAccounts();
    ServiceResponse requestLawnService(Customer customer, LawnMowerCompany lawnMowerCompany);
}

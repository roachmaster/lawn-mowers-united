package com.lessonsbyleo.LawnMowersUnited.service;

import com.lessonsbyleo.LawnMowersUnited.data.*;
import com.lessonsbyleo.LawnMowersUnited.db.adapter.LawnMowerCustomerDBAdapterInf;
import com.lessonsbyleo.LawnMowersUnited.db.adapter.LawnMowersCompanyDbAdapterInf;
import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEventAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.lessonsbyleo.LawnMowersUnited.data.AccountCreationState.*;

@Service
public class LawnMowersUnitedService implements LawnMowersUnitedServiceInf {
    @Autowired
    private LawnMowersCompanyDbAdapterInf lawnMowersCompanyDbAdapter;

    @Autowired
    private LawnMowerCustomerDBAdapterInf lawnMowerCustomerDBAdapterInf;
    @Autowired
    private LawnMowersUnitedEventAdapter lawnMowersUnitedEventAdapter;

    @Override
    public AccountCreationResponse createAccount(LawnMowerCompany lawnMowerCompany) {
        boolean accountExists = doesAccountExistFor(lawnMowerCompany);
        AccountCreationState accountCreationState;
        if(accountExists){
            accountCreationState = CANCELED_ACCOUNT_EXISTS;
        } else {
            createAccountFor(lawnMowerCompany);
            publishNewAccountCreationEventFor(lawnMowerCompany);
            accountCreationState = AccountCreationState.UNDER_REVIEW;
        }
        return new AccountCreationResponse(accountCreationState);
    }

    @Override
    public AccountCreationResponse createAccount(Customer customer) {
        boolean accountExists = doesAccountExistFor(customer);
        AccountCreationState accountCreationState;
        if(accountExists){
            accountCreationState = CANCELED_ACCOUNT_EXISTS;
        } else {
            createAccountFor(customer);
            accountCreationState = ACCOUNT_CREATED;
        }
        return new AccountCreationResponse(accountCreationState);
    }

    private void createAccountFor(Customer customer) {
        lawnMowerCustomerDBAdapterInf.createAccount(customer);
    }

    @Override
    public List<LawnMowerCompany> getLawnMowerCompanyAccounts() {
        return lawnMowersCompanyDbAdapter.getLawnMowerAccounts();
    }

    @Override
    public ServiceResponse requestLawnService(Customer customer, LawnMowerCompany lawnMowerCompany) {
        ServiceResponse response = new ServiceResponse();
        if(Objects.isNull(customer)){
            response.setServiceRequestState(ServiceRequestState.CREATE_ACCOUNT);
        } else {
            ServiceRequestState serviceRequestState = getValidationState(customer,lawnMowerCompany);
            if(Objects.isNull(serviceRequestState)){
                lawnMowersUnitedEventAdapter.publishServiceRequestEvent(customer, lawnMowerCompany);
                serviceRequestState = ServiceRequestState.LAWN_MOWER_COMPANY_NOTIFIED;
            }
            response.setServiceRequestState(serviceRequestState);
        }
        return response;
    }

    private ServiceRequestState getValidationState(Customer customer, LawnMowerCompany lawnMowerCompany) {
        if(!lawnMowerCustomerDBAdapterInf.doesAccountExistFor(customer)){
            return ServiceRequestState.CREATE_ACCOUNT;
        } else if (!lawnMowersCompanyDbAdapter.doesAccountExist(lawnMowerCompany)) {
            return ServiceRequestState.INVALID_LAWN_MOWER_COMPANY;
        }
        return null;
    }

    private void publishNewAccountCreationEventFor(LawnMowerCompany lawnMowerCompany) {
        lawnMowersUnitedEventAdapter.publishNewAccountCreationEventFor(lawnMowerCompany);
    }

    private void createAccountFor(LawnMowerCompany lawnMowerCompany) {
        lawnMowersCompanyDbAdapter.createAccount(lawnMowerCompany);
    }

    private boolean doesAccountExistFor(LawnMowerCompany lawnMowerCompany){
        return lawnMowersCompanyDbAdapter.doesAccountExist(lawnMowerCompany);
    }
    private boolean doesAccountExistFor(Customer customer){
        return lawnMowerCustomerDBAdapterInf.doesAccountExistFor(customer);
    }
}

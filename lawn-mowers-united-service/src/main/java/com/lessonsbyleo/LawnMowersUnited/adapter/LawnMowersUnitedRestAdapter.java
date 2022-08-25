package com.lessonsbyleo.LawnMowersUnited.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.LawnMowersUnited.data.*;
import com.lessonsbyleo.LawnMowersUnited.service.LawnMowersUnitedServiceInf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class LawnMowersUnitedRestAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LawnMowersUnitedRestAdapter.class);
    @Autowired
    private LawnMowersUnitedServiceInf lawnMowersUnitedService;

    @Autowired
    private ObjectWriter objectWriter;
    @RequestMapping(value = "lawnmower/company/account/creation", method = RequestMethod.POST)
    public ResponseEntity<AccountCreationResponse> createLawnMowerAccount(@RequestBody LawnMowerCompany lawnMowerCompany) throws JsonProcessingException {
        logger.debug("Received account creation from {}", objectWriter.writeValueAsString(lawnMowerCompany));
        AccountCreationResponse response = lawnMowersUnitedService.createAccount(lawnMowerCompany);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "lawnmower/companies", method = RequestMethod.GET)
    public ResponseEntity<List<LawnMowerCompany>> getLawnMowerCompanies() {
        logger.debug("Get Lawn Mower Companies");
        List<LawnMowerCompany> response = lawnMowersUnitedService.getLawnMowerCompanyAccounts();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "lawnmower/customer/account/creation", method = RequestMethod.POST)
    public ResponseEntity<AccountCreationResponse> createLawnMowerAccount(@RequestBody Customer customer) throws JsonProcessingException {
        logger.debug("Received account creation {}", objectWriter.writeValueAsString(customer));
        AccountCreationResponse response = lawnMowersUnitedService.createAccount(customer);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "lawnmower/customer/service/request", method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse> createServiceRquest(@RequestBody ServiceRequest serviceRequest) throws JsonProcessingException {
        logger.debug("Received service account creation {}", objectWriter.writeValueAsString(serviceRequest));
        ServiceResponse response = lawnMowersUnitedService.requestLawnService(serviceRequest.getCustomer(), serviceRequest.getLawnMowerCompany());
        return ResponseEntity.ok(response);
    }
}

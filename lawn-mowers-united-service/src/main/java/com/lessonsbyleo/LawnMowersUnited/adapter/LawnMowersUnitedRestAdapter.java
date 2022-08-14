package com.lessonsbyleo.LawnMowersUnited.adapter;

import com.lessonsbyleo.LawnMowersUnited.data.*;
import com.lessonsbyleo.LawnMowersUnited.service.LawnMowersUnitedServiceInf;
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
    @Autowired
    private LawnMowersUnitedServiceInf lawnMowersUnitedService;

    @RequestMapping(value = "lawnmower/company/account/creation", method = RequestMethod.POST)
    public ResponseEntity<AccountCreationResponse> createLawnMowerAccount(@RequestBody LawnMowerCompany lawnMowerCompany) {
        AccountCreationResponse response = lawnMowersUnitedService.createAccount(lawnMowerCompany);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "lawnmower/companies", method = RequestMethod.GET)
    public ResponseEntity<List<LawnMowerCompany>> getLawnMowerCompanies() {
        List<LawnMowerCompany> response = lawnMowersUnitedService.getLawnMowerCompanyAccounts();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "lawnmower/customer/account/creation", method = RequestMethod.POST)
    public ResponseEntity<AccountCreationResponse> createLawnMowerAccount(@RequestBody Customer customer) {
        AccountCreationResponse response = lawnMowersUnitedService.createAccount(customer);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "lawnmower/customer/service/request", method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse> createLawnMowerAccount(@RequestBody ServiceRequest serviceRequest) {
        ServiceResponse response = lawnMowersUnitedService.requestLawnService(serviceRequest.getCustomer(), serviceRequest.getLawnMowerCompany());
        return ResponseEntity.ok(response);
    }
}

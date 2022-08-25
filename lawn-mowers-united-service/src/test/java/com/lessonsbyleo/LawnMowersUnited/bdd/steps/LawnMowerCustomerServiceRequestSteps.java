package com.lessonsbyleo.LawnMowersUnited.bdd.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.LawnMowersUnited.bdd.customer.CustomerTestFactory;
import com.lessonsbyleo.LawnMowersUnited.data.*;
import com.lessonsbyleo.LawnMowersUnited.db.adapter.LawnMowerCustomerDBAdapterTestDouble;
import com.lessonsbyleo.LawnMowersUnited.db.adapter.LawnMowersCompanyDBAdapterTestDouble;
import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.event.consumer.LawnMowerCustomerServiceRequestEventConsumer;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.LawnMowerCustomerServiceRequestEventPublisher;
import com.lessonsbyleo.LawnMowersUnited.notification.email.EmailSender;
import com.lessonsbyleo.LawnMowersUnited.service.LawnMowersUnitedServiceInf;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LawnMowerCustomerServiceRequestSteps {
    @Autowired
    private LawnMowersCompanyDBAdapterTestDouble lawnMowersCompanyDBAdapterTestDouble;
    @Autowired
    private LawnMowerCustomerDBAdapterTestDouble lawnMowerCustomerDBAdapterTestDouble;
    @Autowired
    private LawnMowersUnitedServiceInf lawnMowersUnitedServiceInf;
    @Autowired
    private LawnMowerCustomerServiceRequestEventPublisher lawnMowerCustomerServiceRequestEventPublisher;

    @Autowired
    private LawnMowerCustomerServiceRequestEventConsumer lawnMowerCustomerServiceRequestEventConsumer;

    @Autowired
    private ObjectWriter prettyWriter;
    @Autowired
    private EmailSender emailSender;

    private Scenario scenario;
    private LawnMowerCompany selectedLawnMowerCompany;
    private Customer customer;
    private ServiceResponse serviceResponse;
    private AccountCreationResponse accountCreationResponse;

    @Before
    public void startUp(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void tearDown(){
        reset(lawnMowerCustomerServiceRequestEventPublisher, emailSender);
    }

    @Given("a Lawn mower customer has requested available lawn mower companies")
    public void aLawnMowerCustomerHasRequestedAvailableLawnMowerCompanies() {
        customer = CustomerTestFactory.create();
        lawnMowersCompanyDBAdapterTestDouble.setTestDataEntities();
        List<LawnMowerCompany> lawnMowerCompanyList = lawnMowersUnitedServiceInf.getLawnMowerCompanyAccounts();
        selectedLawnMowerCompany = lawnMowerCompanyList.get(0);
    }

    @And("the Lawn mower customer does not have an Account")
    public void theLawnMowerCustomerDoesNotHaveAnAccount() {
        lawnMowerCustomerDBAdapterTestDouble.delete(customer);
    }

    @And("the Lawn mower customer does have an Account")
    public void theLawnMowerCustomerDoesHaveAnAccount() {
        lawnMowerCustomerDBAdapterTestDouble.createAccount(customer);
    }

    @When("the Lawn customer selects a Lawn Mower Company for a service request")
    public void theLawnCustomerSelectsALawnMowerCompany() {
        serviceResponse = lawnMowersUnitedServiceInf.requestLawnService(customer, selectedLawnMowerCompany);
    }

    @Then("the Lawn Mowers United responds to the customer to create an account")
    public void theLawnMowersUnitedRespondsToTheCustomerToCreateAnAccount() {
        assertEquals(ServiceRequestState.CREATE_ACCOUNT, serviceResponse.getServiceRequestState());
    }

    @Then("the Lawn mowers United responds to the Customer that the Lawn Mower company has been notified")
    public void theLawnMowersUnitedRespondsToTheCustomerThatTheLawnMowerCompanyHasBeenNotified() {
        assertEquals(ServiceRequestState.LAWN_MOWER_COMPANY_NOTIFIED, serviceResponse.getServiceRequestState());
    }

    @Given("a new Lawn mower customer is ready to create an account")
    public void aNewLawnMowerCustomerIsReadyToCreateAnAccount() {
        customer = CustomerTestFactory.create();
        lawnMowerCustomerDBAdapterTestDouble.delete(customer);
    }
    @Given("an existing Lawn mower customer is ready to create an account")
    public void anExistingLawnMowerCustomerIsReadyToCreateAnAccount() {
        customer = CustomerTestFactory.create();
        lawnMowerCustomerDBAdapterTestDouble.createAccount(customer);
    }
    @When("the Lawn mower customer signs up to Lawn Mowers United")
    public void theLawnMowerCustomerSignsUpToLawnMowersUnited() {
        accountCreationResponse = lawnMowersUnitedServiceInf.createAccount(customer);
    }
    @Then("Lawn Mowers United responds to the customer that the new account was created")
    public void lawnMowersUnitedRespondsToTheCustomerThatTheNewAccountWasCreated() {
        assertEquals(AccountCreationState.ACCOUNT_CREATED, accountCreationResponse.getState());
    }

    @Then("Lawn Mowers United responds to the customer that the account exists")
    public void lawnMowersUnitedRespondsToTheCustomerThatTheAccountExists() {
        assertEquals(AccountCreationState.CANCELED_ACCOUNT_EXISTS, accountCreationResponse.getState());
    }

    @Then("a Lawn Mower Customer service request event occurs")
    public void aLawnMowerCustomerServiceRequestEventOccurs() throws JsonProcessingException {
        ArgumentCaptor<LawnMowersUnitedEvent> lawnMowersUnitedEventArgumentCaptor = ArgumentCaptor.forClass(LawnMowersUnitedEvent.class);
        verify(lawnMowerCustomerServiceRequestEventPublisher, times(1)).publish(lawnMowersUnitedEventArgumentCaptor.capture());
        String lawnMowerServiceRequestEventDescription = prettyWriter.writeValueAsString(lawnMowersUnitedEventArgumentCaptor.getValue());
        scenario.log(lawnMowerServiceRequestEventDescription);
    }

    @Given("that a Lawn Mower Customer service request event has occurred")
    public void thatALawnMowerCustomerServiceRequestEventHasOccurred() {
        //Given
        aLawnMowerCustomerHasRequestedAvailableLawnMowerCompanies();
        //And
        theLawnMowerCustomerDoesHaveAnAccount();
        //When
        theLawnCustomerSelectsALawnMowerCompany();
        //Then
        theLawnMowersUnitedRespondsToTheCustomerThatTheLawnMowerCompanyHasBeenNotified();

    }

    @When("Lawn Mowers United consumes the service request event")
    public void lawnMowersUnitedConsumesTheServiceRequestEvent() {
        ArgumentCaptor<LawnMowersUnitedEvent> lawnMowersUnitedEventArgumentCaptor = ArgumentCaptor.forClass(LawnMowersUnitedEvent.class);
        verify(lawnMowerCustomerServiceRequestEventPublisher, times(1)).publish(lawnMowersUnitedEventArgumentCaptor.capture());
        LawnMowersUnitedEvent serviceRequestEvent = lawnMowersUnitedEventArgumentCaptor.getValue();
        lawnMowerCustomerServiceRequestEventConsumer.consume(serviceRequestEvent);
    }

    @Then("the Lawn Mower Company gets notified of the service request")
    public void theLawnMowerCompanyGetsNotifiedOfTheServiceRequest() {
        verify(emailSender, times(1)).send(any());
    }
}

package com.lessonsbyleo.LawnMowersUnited.bdd.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.LawnMowersUnited.bdd.LawnMowersUnitedBddConfig;
import com.lessonsbyleo.LawnMowersUnited.bdd.company.LawnMowerCompanyTestFactory;
import com.lessonsbyleo.LawnMowersUnited.data.AccountCreationState;
import com.lessonsbyleo.LawnMowersUnited.db.adapter.LawnMowersCompanyDBAdapterTestDouble;
import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.data.AccountCreationResponse;
import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.event.consumer.NewAccountCreationEventConsumer;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.inf.NewAccountCreationEventPublisher;
import com.lessonsbyleo.LawnMowersUnited.notification.EmailNotificationAdapterTestDouble;
import com.lessonsbyleo.LawnMowersUnited.service.LawnMowersUnitedServiceInf;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@CucumberContextConfiguration
@ContextConfiguration(
        classes = {LawnMowersUnitedBddConfig.class},
        initializers = {ConfigDataApplicationContextInitializer.class}
)
public class LawnMowerCompanyAccountCreationSteps {
    private Scenario scenario;

    @Autowired
    private LawnMowersUnitedServiceInf lawnMowersUnitedService;

    @Autowired
    private ObjectWriter prettyWriter;
    @Autowired
    private LawnMowersCompanyDBAdapterTestDouble lawnMowersCompanyDbAdapter;
    @Autowired
    private EmailNotificationAdapterTestDouble lawnMowersUnitedNotificationAdapter;

    @Autowired
    private NewAccountCreationEventPublisher newAccountCreationEventPublisher;
    @Autowired
    private NewAccountCreationEventConsumer newAccountCreationEventConsumer;

    private List<LawnMowerCompany> lawnMowerCompanyList;
    private LawnMowerCompany lawnMowerCompany;
    private AccountCreationResponse accountCreationResponse;
    private LawnMowersUnitedEvent newAccountCreationEvent;


    @Before
    public void startUp(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void tearDown(){
        lawnMowersCompanyDbAdapter.resetMock();
        reset(newAccountCreationEventPublisher);
    }

    @Given("a Lawn Mower Company is ready to apply to Lawn Mowers United")
    public void a_lawn_mower_company_is_ready_to_apply_to_lawn_mowers_united() throws JsonProcessingException {
        lawnMowerCompany = LawnMowerCompanyTestFactory.create(1).get(0);
        scenario.log(this.prettyWriter.writeValueAsString(lawnMowerCompany));
    }

    @And("a Lawn Mower Company does not have an existing account")
    public void aLawnMowerCompanyDoesNotHaveAnExistingAccount() {
        lawnMowersCompanyDbAdapter.delete(lawnMowerCompany);
    }

    @When("Lawn Mowers United receives the Lawn Mower Companies application")
    public void lawn_mowers_united_receives_the_lawn_mower_companies_application() {
        this.accountCreationResponse = this.lawnMowersUnitedService.createAccount(lawnMowerCompany);
    }
    @Then("Lawn Mowers United replies to the Lawn Mower Companies with the applications initial status")
    public void lawn_mowers_united_replies_to_the_lawn_mower_companies_with_the_applications_initial_status() {
        assertNotNull(this.accountCreationResponse);
    }
    @Then("Lawn Mowers United verifies the Lawn Mower Companies accounts does not exist")
    public void lawnMowersUnitedVerifiesTheLawnMowerCompaniesAccountsDoesNotExist() {
        lawnMowersCompanyDbAdapter.verifyDoesAccountExistWasCalled(1);
    }

    @Then("the Lawn Mower Companies account application is under review")
    public void theLawnMowerCompaniesAccountApplicationIsUnderReview() {
        assertEquals(AccountCreationState.UNDER_REVIEW, accountCreationResponse.getState());
        scenario.log("AccountCreationState: " + accountCreationResponse.getState().name());
    }

    @Then("a new account creation event occurs")
    public void aNewAccountCreationEventOccurs() throws JsonProcessingException {
        ArgumentCaptor<LawnMowersUnitedEvent> lawnMowersUnitedEventArgumentCaptor = ArgumentCaptor.forClass(LawnMowersUnitedEvent.class);
        verify(newAccountCreationEventPublisher, times(1)).publish(lawnMowersUnitedEventArgumentCaptor.capture());
        newAccountCreationEvent = lawnMowersUnitedEventArgumentCaptor.getValue();
        String newAccountCreationEventDescription = prettyWriter.writeValueAsString(lawnMowersUnitedEventArgumentCaptor.getValue());
        scenario.log(newAccountCreationEventDescription);
    }

    @Given("Lawn Mowers United created a new Lawn Mower Company Account")
    public void lawnMowersUnitedCreatedANewLawnMowerCompanyAccount() throws JsonProcessingException {
        a_lawn_mower_company_is_ready_to_apply_to_lawn_mowers_united();
        aLawnMowerCompanyDoesNotHaveAnExistingAccount();
        lawn_mowers_united_receives_the_lawn_mower_companies_application();
    }

    @When("Lawn Mowers United receives a request for Lawn Mower Companies")
    public void lawnMowersUnitedReceivesARequestForLawnMowerCompanies() {
        lawnMowerCompanyList = this.lawnMowersUnitedService.getLawnMowerCompanyAccounts();
    }

    @Then("the Lawn Mower responds with the Lawn Mower Companies")
    public void theLawnMowerRespondsWithTheLawnMowerCompanies() {
        assertNotNull(this.lawnMowerCompanyList);
    }

    @And("the new Lawn Mower Company is in the list")
    public void theNewLawnMowerCompaniesIsInTheList() {
        boolean inList = false;
        for(LawnMowerCompany company: lawnMowerCompanyList){
            if(company.equals(lawnMowerCompany)){
                inList = true;
            }
        }
        assertTrue(inList);
    }

    @And("a Lawn Mower Company does have an existing account")
    public void aLawnMowerCompanyDoesHaveAnExistingAccount() {
        lawnMowersCompanyDbAdapter.createAccount(lawnMowerCompany);
    }


    @Then("Lawn Mowers United verifies the Lawn Mower Companies accounts does exist")
    public void lawnMowersUnitedVerifiesTheLawnMowerCompaniesAccountsDoesExist() {
        lawnMowersCompanyDbAdapter.verifyDoesAccountExistWasCalled(1);
    }

    @And("the Lawn Mowers Companies account application has been canceled")
    public void theLawnMowersCompaniesAccountApplicationHasBeenCanceled() {
        assertEquals(AccountCreationState.CANCELED_ACCOUNT_EXISTS, accountCreationResponse.getState());
        scenario.log("AccountCreationState: " + accountCreationResponse.getState().name());
    }

    @Given("that there are no Lawn Mower Companies")
    public void thatThereAreNoLawnMowerCompanies() {
        lawnMowersCompanyDbAdapter.deleteAll();
    }

    @And("there are {int} Lawn Mower Companies")
    public void thereAreLawnMowerCompanies(int num) {
        assertEquals(num, lawnMowerCompanyList.size());
    }

    @Given("that there are {int} Lawn Mower Companies")
    public void thatThereAreLawnMowerCompanies(int num) throws JsonProcessingException {
        List<LawnMowerCompany> lawnMowerCompanies = LawnMowerCompanyTestFactory.create(num);
        lawnMowerCompanies.forEach(company -> lawnMowersCompanyDbAdapter.createAccount(company));
        scenario.log(this.prettyWriter.writeValueAsString(lawnMowerCompanies));
    }

    @Given("that new account creation event has occurred")
    public void thatNewAccountCreationEventHasOccurred() throws JsonProcessingException {
        a_lawn_mower_company_is_ready_to_apply_to_lawn_mowers_united();
        aLawnMowerCompanyDoesNotHaveAnExistingAccount();
        lawn_mowers_united_receives_the_lawn_mower_companies_application();
        aNewAccountCreationEventOccurs();
    }

    @When("Lawn Mowers United consumes the new account creation event")
    public void lawnMowersUnitedConsumesTheNewAccountCreationEvent() {
        newAccountCreationEventConsumer.consume(newAccountCreationEvent);
    }

    @Then("the account owner is notified of the creation")
    public void theAccountOwnerIsNotifiedOfTheCreation() throws JsonProcessingException {
        lawnMowersUnitedNotificationAdapter.captureArgumentsNotifyLawnMowerCompanyEvent(prettyWriter, scenario);
    }
}

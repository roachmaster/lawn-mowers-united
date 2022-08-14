Feature: In Memory Component Test - Lawn Mower Customer Service Requests

  Scenario: A new Lawn Mower Customer selects a Lawn Mower Company to request service
    Given a Lawn mower customer has requested available lawn mower companies
    And the Lawn mower customer does not have an Account
    When the Lawn customer selects a Lawn Mower Company for a service request
    Then the Lawn Mowers United responds to the customer to create an account

  Scenario: A new Lawn Mower Customer creates an account
    Given a new Lawn mower customer is ready to create an account
    When the Lawn mower customer signs up to Lawn Mowers United
    Then Lawn Mowers United responds to the customer that the new account was created

  Scenario: An existing Lawn Mower Customer creates an account
    Given an existing Lawn mower customer is ready to create an account
    When the Lawn mower customer signs up to Lawn Mowers United
    Then Lawn Mowers United responds to the customer that the account exists

  Scenario: An existing Lawn Mower Customer selects a Lawn Mower Company to request service
    Given a Lawn mower customer has requested available lawn mower companies
    And the Lawn mower customer does have an Account
    When the Lawn customer selects a Lawn Mower Company for a service request
    Then the Lawn mowers United responds to the Customer that the Lawn Mower company has been notified

  Scenario: A Lawn Mower Customer Service Request Event published
    Given that a Lawn Mower Customer service request event has occurred
    When Lawn Mowers United consumes the service request event
    Then the Lawn Mower Company gets notified of the service request
Feature: In Memory Component Test - Lawn Mower Company Account Creation

  Scenario: A Lawn Mower Company applies to Lawn Mowers United
    Given a Lawn Mower Company is ready to apply to Lawn Mowers United
    When Lawn Mowers United receives the Lawn Mower Companies application
    Then Lawn Mowers United replies to the Lawn Mower Companies with the applications initial status

  Scenario: New Lawn Mower Company applies
    Given a Lawn Mower Company is ready to apply to Lawn Mowers United
    And a Lawn Mower Company does not have an existing account
    When Lawn Mowers United receives the Lawn Mower Companies application
    Then Lawn Mowers United verifies the Lawn Mower Companies accounts does not exist
    And the Lawn Mower Companies account application is under review

  Scenario: New account creation Event published
    Given a Lawn Mower Company is ready to apply to Lawn Mowers United
    And a Lawn Mower Company does not have an existing account
    When Lawn Mowers United receives the Lawn Mower Companies application
    Then a new account creation event occurs

  Scenario: New account creation Event consumed
    Given that new account creation event has occurred
    When Lawn Mowers United consumes the new account creation event
    Then the account owner is notified of the creation

  Scenario: Lawn Mower Company Account Creation is persisted
    Given Lawn Mowers United created a new Lawn Mower Company Account
    When Lawn Mowers United receives a request for Lawn Mower Companies
    Then the Lawn Mower responds with the Lawn Mower Companies
    And the new Lawn Mower Company is in the list

  Scenario: Existing Lawn Mower Company applies
    Given a Lawn Mower Company is ready to apply to Lawn Mowers United
    And a Lawn Mower Company does have an existing account
    When Lawn Mowers United receives the Lawn Mower Companies application
    Then Lawn Mowers United verifies the Lawn Mower Companies accounts does exist
    And the Lawn Mowers Companies account application has been canceled
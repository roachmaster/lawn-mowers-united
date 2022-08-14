Feature: In Memory Component Test - Lawn Mower Companies Account requests
  Scenario: Request for Lawn Mower Companies and no lawn mower companies exist
    Given that there are no Lawn Mower Companies
    When Lawn Mowers United receives a request for Lawn Mower Companies
    Then the Lawn Mower responds with the Lawn Mower Companies
    And there are 0 Lawn Mower Companies

  Scenario: Request for Lawn Mower Companies and 1 lawn mower companies exist
    Given that there are 1 Lawn Mower Companies
    When Lawn Mowers United receives a request for Lawn Mower Companies
    Then the Lawn Mower responds with the Lawn Mower Companies
    And there are 1 Lawn Mower Companies

  Scenario: Request for Lawn Mower Companies and 2 lawn mower companies exist
    Given that there are 2 Lawn Mower Companies
    When Lawn Mowers United receives a request for Lawn Mower Companies
    Then the Lawn Mower responds with the Lawn Mower Companies
    And there are 2 Lawn Mower Companies

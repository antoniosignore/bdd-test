Feature: Smoke test happy trail

  Background:
    Given Host property: "salesforce-integration"
    Given Account name:  "testuser-42"

  Scenario: Create account, getaccount, get all accounts, update account, delete account via REST

    When I create a salesforce user
    Then I get a CREATED response
    Then I get a OK response
    When I patch a session
    Then I get a OK response

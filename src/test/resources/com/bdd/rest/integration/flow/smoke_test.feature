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

    Given Connect to: "replist"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response
    When I retrieve list by id
    Then I get a OK response

    When I add a product: "DA9618"
    Then I get a OK response
    When I PUT a need for product: "DA9618"
    Then I get a OK response
    When I PUT a need for product: "DA9618"
    Then I get a OK response
    When I PUT a need for product: "DA9618"
    Then I get a OK response

    When I retrieve all articles
    Then I get a OK response
    When I retrieve all lists
    Then I get a OK response

    When I change status to "OPEN"
    Then I get a OK response

    When I delete list
    Then I get a OK response

    Given Connect to: "session"
    When I delete a session
    Then I get a OK response



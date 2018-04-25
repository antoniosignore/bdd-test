Feature: create Replist

  Background:
    Given I am username: "antonio.signore" host: "http://localhost:8888"

  Scenario: Create list
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:9999"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response


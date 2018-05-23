Feature: create Replist

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"

  Scenario: Create product, add need flow
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:9999"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I add a product: "C77124"
    Then I get a OK response
    When I PUT a need for product: "PID1"
    Then I get a OK response
    When I PUT a need for product: "PID1"
    Then I get a OK response
    When I PUT a need for product: "PID1"
    Then I get a OK response
    When I retrieve all articles
    Then I get a OK response
    When I retrieve all lists
    Then I get a OK response
    When I change status to "OPEN"
    Then I get a OK response



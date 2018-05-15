Feature: create Replist

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"

  Scenario: Create Session
    When I create a session
    Then I get a CREATED response
    Given Host: "http://deheremap6918:9999"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response


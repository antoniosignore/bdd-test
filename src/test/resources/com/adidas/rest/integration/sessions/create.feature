Feature: create profile

  Background:
    Given I am username: "antonio.signore" host: "http://localhost:8888"

  Scenario: Create Session
    When I create a session
    Then I get a CREATED response


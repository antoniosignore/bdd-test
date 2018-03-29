Feature: create profile

  Background:
    Given I am username: "antonio.signore" host: "http://localhost:9090"

  Scenario: Create a session
    When I create a session
    Then I get a CREATED response
    When I search for the session by deviceId


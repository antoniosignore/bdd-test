Feature: create profile

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"

  Scenario: Create a session
    When I create a session
    Then I get a CREATED response
    When I search for the session by deviceId


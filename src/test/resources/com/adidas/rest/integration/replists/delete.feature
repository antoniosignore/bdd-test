Feature: delete replist

  Background:
    Given I am username: "antonio.signore" host: "http://localhost:9090"

  Scenario: List all sessions
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:8080"
    When I delete all lists
    Then I get a OK response


Feature: delete replist

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"

  Scenario: List all sessions
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:8080"
    When I delete all lists
    Then I get a OK response


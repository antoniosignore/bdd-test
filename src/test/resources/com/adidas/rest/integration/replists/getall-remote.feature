Feature: list all profiles

  Background:
    Given I am username: "antonio.signore" host: "http://deheremap6918:8888"

  Scenario: Create replist
    When I create a session
    Then I get a CREATED response
    Given Host: "http://deheremap6918:9999"
    When I retrieve all lists
    Then I get a OK response


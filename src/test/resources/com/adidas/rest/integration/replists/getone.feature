Feature: Get one article bean

  Background:
    Given I am username: "antonio.signore" host: "http://localhost:9090"

  Scenario: Get one article bean
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:8080"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all products
    Then I get a OK response


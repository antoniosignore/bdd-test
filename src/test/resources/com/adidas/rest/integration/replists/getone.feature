Feature: Get one article bean

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"

  Scenario: Get one article bean
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:8080"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all articles
    Then I get a OK response


Feature: Get one article bean

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"

  Scenario: Get one article bean
    When I create a session
    Then I get a CREATED response
    Given Host: "http://deheremap6918:9999"
    When I retrieve a product
    Then I get a OK response


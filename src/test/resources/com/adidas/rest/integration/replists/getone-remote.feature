Feature: Get one article bean

  Background:
    Given I am username: "antonio.signore" host: "http://deheremap6918:8888"

  Scenario: Get one article bean
    When I create a session
    Then I get a CREATED response
    Given Host: "http://deheremap6918:9999"
    When I retrieve a product
    Then I get a OK response


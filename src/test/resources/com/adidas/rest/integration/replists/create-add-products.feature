Feature: create Replist

  Background:
    Given Host: "http://localhost:8888"
    Given Username: "tester"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"


  Scenario: Create Session
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:8080"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response
    When I add a product: "PID1"
    Then I get a OK response
    When I add a product: "PID2"
    Then I get a OK response
    When I PUT a need for product: "PID1"
    Then I get a OK response
    When I PUT a need for product: "PID2"
    Then I get a OK response
    When I PUT a need for product: "PID1"
    Then I get a OK response
    When I retrieve all articles
    Then I get a OK response
    When I change status to "OPEN"
    Then I get a OK response


Feature: create Replist

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"
    Given Username: "antonio.signore"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  Scenario: Create list
    When I create a session
    Then I get a CREATED response
    Given Host: "http://localhost:9999"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response


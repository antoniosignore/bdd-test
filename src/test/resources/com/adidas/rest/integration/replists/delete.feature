Feature: delete replist

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  Scenario: Delete all lists
    When I create a session
    Then I get a CREATED response
    When I patch a session
    Then I get a OK response
    Given Host: "http://localhost:9999"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I delete list
    Then I get a OK response
    Given Host: "http://localhost:8888"
    When I delete a session
    Then I get a OK response


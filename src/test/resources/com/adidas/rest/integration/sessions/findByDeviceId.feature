Feature: create profile

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://localhost:8888"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  Scenario: Create a session
    When I create a session
    Then I get a CREATED response
    When I search for the session by deviceId
    Then I get a OK response


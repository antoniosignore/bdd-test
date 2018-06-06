Feature: create profile

  Background:
    Given Connect to: "session"
    Given Username: "tester"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  @SmokeTest
  Scenario: Create and Delete Session
#    When I get a session by device id - need to manage the exception
#    Then I get a NOT_FOUND response
    When I create a session
    Then I get a CREATED response
    When I get a session by device id
    Then I get a OK response
    When I patch a session
    Then I get a OK response
    When I get a session by device id
    Then I get a OK response
    When I get a session by store id
    Then I get a OK response
    When I get a session by store id and role
    Then I get a OK response
    When I delete a session
    Then I get a OK response


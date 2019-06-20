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

    When I create a session
    Then I get a CREATED response
    Then Expected response is the same as in file: "json/session_created.json" excluding "authToken,deviceId,user.name"

    When I get a session by device id
    Then I get a OK response
    When I patch a session
    Then I get a OK response
    Then Expected response is the same as in file: "json/session_created_patched.json" excluding "authToken,deviceId,user.name"


    When I get a session by device id
    Then I get a OK response
    Then Expected response is the same as in file: "json/session_created_patched.json" excluding "authToken,deviceId,user.name"

    When I get sessions by store id
    Then I get a OK response
    When I get sessions by store id and role
    Then I get a OK response
    When I delete a session
    Then I get a OK response


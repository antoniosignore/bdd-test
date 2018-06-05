Feature: Smoke test develop environment

  Background:
    Given Username: "antonio.signore"
    Given Connect to: "session"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  @SmokeTest
  Scenario: Create list, add product, add items need flow
    When I create a session
    Then I get a CREATED response
    When I get a session by device id
    Then I get a OK response
    When I patch a session
    Then I get a OK response
    When I get a session by store id
    Then I get a OK response
    When I get a session by store id and role
    Then I get a OK response
    Given Connect to: "replist"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response
    When I retrieve list by id
    Then I get a OK response
    When I add a product: "DA9618"
    Then I get a OK response
    When I PUT a need for product: "DA9618"
    Then I get a OK response
    When I PUT a need for product: "DA9618"
    Then I get a OK response
    When I PUT a need for product: "DA9618"
    Then I get a OK response
    When I retrieve all articles
    Then I get a OK response
    When I retrieve all lists
    Then I get a OK response
    When I change status to "OPEN"
    Then I get a OK response
    When I delete list
    Then I get a OK response
    Given Connect to: "session"
    When I delete a session
    Then I get a OK response



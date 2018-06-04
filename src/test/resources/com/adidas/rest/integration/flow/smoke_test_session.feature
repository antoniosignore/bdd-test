Feature: create profile

  Background:
    Given Host: "http://develop.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
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


Feature: create profile flow

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://develop.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
#    Given Host: "http://localhost:8888"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  Scenario: Create and Delete Session
    When I create a session
    Then I get a CREATED response
    When I get a session
    Then I get a OK response
    When I patch a session
    Then I get a OK response
    When I delete a session
    Then I get a OK response


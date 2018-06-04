Feature: Smoke test develop environment

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://develop.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
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
    Given Host: "http://develop.replist-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response
    When I retrieve list by id
    Then I get a OK response
    When I change status to "OPEN"
    Then I get a OK response
    When I delete list
    Then I get a OK response
    Given Host: "http://develop.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
    When I delete a session
    Then I get a OK response



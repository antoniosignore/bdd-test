Feature: Smoke test develop environment

  Background:
    Given Username: "antonio.signore"
    Given Host: "http://staging.session-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net"
    Given StoreId: "US470179"
    Given DeviceId
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  Scenario: Create list, add product, add items need flow
    When I create a session
    Then I get a CREATED response
    When I patch a session
    Then I get a OK response
    Given Host: "http://staging.replist-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
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
    Given Host: "http://staging.session-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net"
    When I delete a session
    Then I get a OK response



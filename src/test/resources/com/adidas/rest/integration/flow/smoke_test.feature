Feature: create Replist

  Background:
    Given Username: "antonio.signore3"
    Given Host: "http://develop.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
#    Given Host: "http://localhost:8888"
    Given StoreId: "US470179"
    Given DeviceId: "F1F108BE-D300-4352-8351-81851416FD66"
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "push_token"

  Scenario: Create list, add product, add items need flow
    When I create a session
    Then I get a CREATED response
    Given Host: "http://develop.replist-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I get a product: "DA9618"
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



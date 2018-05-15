Feature: create profile

  Background:
    Given Username: "antonio.signore"
#    Given Host: "http://develop.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"
    Given Host: "http://localhost:8888"
    Given StoreId: "US470179"
    Given DeviceId: "F1F108BE-D300-4352-8351-81851416FD6A"
    Given Role: "SalesFloor"
    Given Scale: "CN"
    Given BundleId: "com.adidas.dev.replenishment"
    Given PushToken: "blah"

  Scenario: Create Session
    When I create a session
    Then I get a CREATED response
    When I patch a session
    Then I get a OK response


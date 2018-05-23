Feature: create Replist

# Given Host: "http://staging.session-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net"

  Background:
    Given Username: "antonio.signore"
    Given Branch: "develop"
    Given Server suffix: "sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net"
    Given Service: "session-service"

  Scenario: Create list
    When I create a session
    Then I get a CREATED response
    Given Service: "replist-service"
    When I create a replist with name: "Mylist"
    Then I get a CREATED response
    When I retrieve all lists
    Then I get a OK response


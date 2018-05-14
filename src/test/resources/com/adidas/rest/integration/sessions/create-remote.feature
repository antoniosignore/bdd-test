Feature: create profile

  Background:
    Given I am username: "antonio.signore" host: "http://chore-add-unit-tests.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"

  Scenario: Create Session
    When I create a session
    Then I get a CREATED response


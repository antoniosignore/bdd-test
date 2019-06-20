Feature: smoke test country service

  Background:
    Given Host: "http://develop.size-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"

  @SmokeTest
  Scenario: get sizes
    When I get all sizes
    Then I get a OK response
    When I get sizePage "S1"
    Then I get a OK response
    When I get sizePage "S1" and scale "US"
    Then I get a OK response


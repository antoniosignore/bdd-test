Feature: smoke test country service

  Background:
#    Given Host: "http://localhost:8888"
    Given Host: "http://develop.country-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"

  @SmokeTest
  Scenario: get countries
    When I get all countries
    Then I get a OK response
    When I get a country: "IT"
    Then I get a OK response
    When I head a country: "IT"
    Then I get a NO_CONTENT response



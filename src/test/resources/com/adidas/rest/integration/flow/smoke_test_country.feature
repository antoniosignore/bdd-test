Feature: smoke test country service

  Background:
    Given Connect to: "country"

  @SmokeTest
  Scenario: get countries
    When I get all countries
    Then I get a OK response
    When I get a country: "IT"
    Then I get a OK response
    When I head a country: "IT"
    Then I get a NO_CONTENT response



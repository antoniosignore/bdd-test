Feature: smoke test country service

  Background:
#    Given Host: "http://localhost:8888"
    Given Host: "http://develop.size-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net"

  @SmokeTest
  Scenario: get sizes
    When I get all sizes
    Then I get a OK response
    When I get sizePage ""
    Then I get a OK response
    When I get sizePage "" and scale ""
    Then I get a OK response



  // Swagger info section-------------------------------------------------
  @ApiOperation(value = "", notes = "Gets the size scales list", response = String.class)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
  @ApiResponse(code = 404, message = "Not found response")})
  // Swagger info section-------------------------------------------------
  @RequestMapping(value = "/sizePage/{sizePage}",
  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<SalesSizesMultiCountries> getSizesForSizePage(
  @PathVariable(value = "sizePage") String sizePage);

  // Swagger info section-------------------------------------------------
  @ApiOperation(value = "", notes = "Gets the size scales list", response = String.class)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
  @ApiResponse(code = 404, message = "Not found response")})
  // Swagger info section-------------------------------------------------
  @RequestMapping(value = "/sizePage/{sizePage}/scale/{scaleCode}",
  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<SalesSizesCountry> getSizesForSizePageAndScaleCode(
  @PathVariable(value = "sizePage") String sizePage,
  @PathVariable(value = "scaleCode") String scaleCode);

package com.adidas.rest.integration;

import com.adidas.model.model.Country;
import com.adidas.rest.integration.utils.Utils;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Slf4j
@CucumberStepsDefinition
public class CountriesStepdefs {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private World world;

    @When("^I get all countries$")
    public void get() {
        String url = world.getHost() + "/countries";
        log.debug("url = " + url);
        final ResponseEntity<Set<Country>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, Utils.createHttpEntityWithToken(world), new ParameterizedTypeReference<Set<Country>>() {
                });

        assert responseEntity != null;
        if (responseEntity.getBody() != null) {
            Utils.json("Response Entity :", responseEntity);
            Utils.json("Response Body   :", responseEntity.getBody());
            world.setResponse(responseEntity);
            Set<Country> body = responseEntity.getBody();
            Utils.json("Countries", body);
            assert body != null;
        }
    }

    @When("^I get a country: \"([^\"]*)\"$")
    public void getCountry(String country) {
        String url = world.getHost() + "/countries/" + country;
        final ResponseEntity<Country> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, Utils.createHttpEntityWithToken(world), Country.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            Utils.json("GET replist service:", responseEntity.getBody());
            Country body = responseEntity.getBody();
            world.setCountry(body);
        }
        world.setResponse(responseEntity);
    }


    @When("^I head a country: \"([^\"]*)\"$")
    public void head(String country) {
        String url = world.getHost() + "/countries/" + country;
        final ResponseEntity<Void> responseEntity =
                restTemplate.exchange(url, HttpMethod.HEAD, Utils.createHttpEntityWithToken(world), Void.class);
        world.setResponse(responseEntity);
    }
}
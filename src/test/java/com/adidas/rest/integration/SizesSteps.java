package com.adidas.rest.integration;

import com.adidas.model.model.SalesSizesCountry;
import com.adidas.model.model.SalesSizesMultiCountries;
import com.adidas.model.model.Scale;
import com.adidas.rest.integration.utils.Utils;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
@CucumberStepsDefinition
public class SizesSteps {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private World world;

    @When("^I get all sizes$")
    public void iGetAllSizes() {
        String url = world.getHost() + "/scales";
        final ResponseEntity<List<Scale>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, Utils.createHttpEntityWithToken(world),
                        new ParameterizedTypeReference<List<Scale>>() {
                        });

        assert responseEntity != null;
        if (responseEntity.getBody() != null) {
            Utils.json("Response Entity :", responseEntity);
            Utils.json("Response Body   :", responseEntity.getBody());
            world.setResponse(responseEntity);
            List<Scale> body = responseEntity.getBody();
            Utils.json("Sizes", body);
            assert body != null;
        }
    }

    @When("^I get sizePage \"([^\"]*)\"$")
    public void iGetSizePage(String arg0) {
        String url = world.getHost() + "/sizes/sizePage/" + arg0;
        System.out.println("url = " + url);

        final ResponseEntity<SalesSizesMultiCountries> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, Utils.createHttpEntityWithToken(world),
                        SalesSizesMultiCountries.class);

        assert responseEntity != null;
        if (responseEntity.getBody() != null) {
            Utils.json("Response Entity :", responseEntity);
            world.setResponse(responseEntity);
            SalesSizesMultiCountries body = responseEntity.getBody();
            Utils.json("sizePage", body);
            assert body != null;
        }
    }

    @When("^I get sizePage \"([^\"]*)\" and scale \"([^\"]*)\"$")
    public void iGetSizePageAndScale(String arg0, String arg1) {
        String url = world.getHost() + "/sizes/sizePage/" + arg0 + "/scale/" + arg1;

        log.debug("URL = " + url);

        final ResponseEntity<SalesSizesCountry> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, Utils.createHttpEntityWithToken(world),
                        SalesSizesCountry.class);

        assert responseEntity != null;
        if (responseEntity.getBody() != null) {
            Utils.json("Response Entity :", responseEntity);
            world.setResponse(responseEntity);
            SalesSizesCountry body = responseEntity.getBody();
            assert body != null;
        }
    }
}

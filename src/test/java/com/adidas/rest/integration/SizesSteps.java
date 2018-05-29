package com.adidas.rest.integration;

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
    public void iGetAllSizes() throws Throwable {
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
}

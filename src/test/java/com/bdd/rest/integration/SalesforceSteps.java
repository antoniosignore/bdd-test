package com.bdd.rest.integration;

import com.bdd.configuration.EnvironmentConfiguration;
import com.bdd.model.model.Replist;
import com.bdd.model.model.ReplistBean;
import com.bdd.representations.beans.Account;
import com.bdd.representations.beans.AccountBean;
import com.bdd.representations.beans.SFResponse;
import com.bdd.rest.integration.utils.Utils;
import com.google.common.collect.ImmutableList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
public class SalesforceSteps {

    @Autowired
    private World world;

    @Autowired
    private RestTemplate restTemplate;

    @Given("^Account name:  \"([^\"]*)\"$")
    public void username(String username) {
        log.debug("username = " + username);
        world.setUsername(username +":" + UUID.randomUUID().toString());
    }

    @When("^I create a salesforce user$")
    public void iCreateASalesforceUser() {

        String url = world.getHost() + "/account";

        AccountBean salesforceAccount = AccountBean.builder().name(world.getUsername()).sapIdentifier__c("random090909").build();

        final ResponseEntity<SFResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                createHttpEntity(salesforceAccount),
                SFResponse.class);
        if (responseEntity.getBody() != null) {
            Utils.json("Response entity:" , responseEntity);
            SFResponse body = responseEntity.getBody();
            world.setAccount(body);
        }


        world.setResponse(responseEntity);
    }

    private HttpEntity createHttpEntity(Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return new HttpEntity<>(body, httpHeaders);
    }

}

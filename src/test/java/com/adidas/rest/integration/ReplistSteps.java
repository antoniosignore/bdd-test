package com.adidas.rest.integration;

import com.adidas.model.model.*;
import com.adidas.rest.integration.utils.Utils;
import com.google.common.collect.ImmutableList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@CucumberStepsDefinition
public class ReplistSteps {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private World world;

    private HttpEntity createHttpEntityWithToken(Object body) {
        final String authHeader = "Bearer " + world.getAuthToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", ImmutableList.of(authHeader));
        return new HttpEntity<>(body, httpHeaders);
    }

    private HttpEntity createHttpEntityWithToken() {
        final String authHeader = "Bearer " + world.getAuthToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", ImmutableList.of(authHeader));
        return new HttpEntity<>(httpHeaders);
    }

    @When("^I create a replist with name: \"([^\"]*)\"$")
    public void I_create_a_replist(String replistName) throws Throwable {
        String url = world.getHost() + "/replists";

        ReplistBean name = new ReplistBean();
        name.name = replistName;

        final ResponseEntity<Replist> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                createHttpEntityWithToken(name),
                Replist.class);
        if (responseEntity.getBody() != null) {
            Utils.json("Response entity:" , responseEntity);
            Replist body = responseEntity.getBody();
            world.setReplist(body);
            world.setListId(body.id);
        }

        world.setResponse(responseEntity);
    }

    @When("^I delete list")
    public void I_delete_a_replist() {
        String url = world.getHost() + "/replists/" + world.getListId();
        log.debug("------>  url = " + url);
        final ResponseEntity<Void> responseEntity =
                restTemplate.exchange(url, HttpMethod.DELETE, createHttpEntityWithToken(), Void.class);
        Utils.json("Response entity:" , responseEntity);
        world.setResponse(responseEntity);
    }

    @When("^I retrieve all lists$")
    public void I_get_all() throws Throwable {
        String url = world.getHost() + "/replists";
        final ResponseEntity<List<Replist>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                createHttpEntityWithToken(),
                new ParameterizedTypeReference<List<Replist>>() {
                });

        if (responseEntity.getBody() != null) {
            Utils.json("Response entity:" , responseEntity);
            List<Replist> list = responseEntity.getBody();
            world.setCounter(list.size());
        }
        world.setResponse(responseEntity);
    }

    // I retrieve list by id
    @When("^I retrieve list by id$")
    public void I_get_by_id() throws Throwable {
        String url = world.getHost() + "/replists/" + world.getListId();
        final ResponseEntity<Replist> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                createHttpEntityWithToken(),
                Replist.class);

        if (responseEntity.getBody() != null) {
            Utils.json("Response entity:" , responseEntity);
            Replist body = responseEntity.getBody();
            Utils.json(body);
            world.setReplist(body);
            world.setListId(body.id);
        }
        world.setResponse(responseEntity);
    }


    @When("^I retrieve a product$")
    public void iRetrieveAProduct() throws Throwable {
        String url = world.getHost() + "/replists/" + world.getReplist().id + "/articles/1";

        final ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET,
                createHttpEntityWithToken(), String.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            log.debug("", responseEntity.getBody());

        }
        world.setResponse(responseEntity);
    }

    @When("^I retrieve all articles$")
    public void iRetrieveAProducts() throws Throwable {
        String url = world.getHost() + "/replists/" + world.getReplist().id + "/articles";

        final ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET,
                createHttpEntityWithToken(), String.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            log.debug("", responseEntity.getBody());

        }

        world.setResponse(responseEntity);
    }

    @Given("^I change status to \"([^\"]*)\"$")
    public void I_changestatus(final String statusCode) throws Throwable {
        String url = world.getHost() + "/replists/" + world.getReplist().id;

        UpdateBean bean = new UpdateBean();
        bean.status = ReplistStatusEnum.OPEN;

        final ResponseEntity<Replist> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PATCH,
                createHttpEntityWithToken(bean),
                Replist.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            Replist body = responseEntity.getBody();
            log.debug("body : " + body);
            Utils.json(body);
            world.setReplist(body);
        }

        world.setResponse(responseEntity);
    }


    @When("^I add a product: \"([^\"]*)\"$")
    public void addProduct(String pid) {
        String url = world.getHost() + "/replists/" + world.getReplist().id + "/articles/" + pid;
        log.debug("GET url = " + url);

        world.setArticleId(pid);

        ArticleBean articleBean = new ArticleBean();
        articleBean.articleNumber = pid;
        articleBean.brand = "Adidas";
        articleBean.colorway = "colorway";
        articleBean.gender = "kinder";
        articleBean.name = "PIPO";

        final ResponseEntity<ArticleBean> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, createHttpEntityWithToken(articleBean), ArticleBean.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            Utils.json("GET replist service:", responseEntity.getBody());
            world.setSizesGrid(responseEntity.getBody());
        }
        world.setResponse(responseEntity);
    }


    @When("^I PUT a need for product: \"([^\"]*)\"$")
    public void iAddANeed(String pid) throws Throwable {
        log.debug("world = " + world);

        String url = world.getHost() + "/replists/" + world.getReplist().id + "/articles/" + pid;

        ArticleBean sizesGrid = world.getSizesGrid();
        ArticleItemBean articleItemBean = sizesGrid.sizes.get(0);
        articleItemBean.amount = 5L;

        final ResponseEntity<ArticleBean> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT,
                createHttpEntityWithToken(sizesGrid), ArticleBean.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            log.debug("", responseEntity.getBody());

            Utils.json("Response from replist service:", responseEntity.getBody());

            world.setSizesGrid(responseEntity.getBody());
        }
        world.setResponse(responseEntity);

    }


}

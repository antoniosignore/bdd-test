package com.adidas.rest.integration;


import com.adidas.model.model.*;
import com.adidas.rest.integration.utils.Utils;
import com.google.common.collect.ImmutableList;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CucumberStepsDefinition
public class UserSteps {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CommonSteps.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private World world;

    private <T> HttpEntity<T> createHttpEntity(final T entity) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return new HttpEntity<>(entity, httpHeaders);
    }

    private HttpEntity createHttpEntity(final String username) {
        Session session = new Session();
        session.deviceId = "my_device";
        session.role = Role.SalesFloor;
        session.storeId = "NYC_Store";
        session.username = username;
        session.sizeScale = "pippo";
        return new HttpEntity<>(session);
    }

    private HttpEntity createHttpEntityWithTokenAndBody(Object body) {
        final String authHeader = "Bearer " + world.getAuthToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", ImmutableList.of(authHeader));
        return new HttpEntity<>(body, httpHeaders);
    }

    private HttpEntity createHttpEntityWithTokenAndBody() {
        final String authHeader = "Bearer " + world.getAuthToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", ImmutableList.of(authHeader));
        return new HttpEntity<>(httpHeaders);
    }

    @When("^I create a session$")
    public void I_create_a_session() throws Throwable {
        String url = world.getHost() + "/sessions";
        final ResponseEntity<Session> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                createHttpEntity(world.getUsername()),
                Session.class);
        if (responseEntity.getBody() != null) {
            Utils.json(responseEntity);

            Session body = responseEntity.getBody();

            Utils.json(body);
            world.setDeviceId(body.deviceId);
            world.setAuthToken(body.authToken);
            world.setSession(body);
        }
        world.setResponse(responseEntity);
    }

    @When("^I create a profile$")
    public void I_create_a_profile() throws Throwable {
        String url = world.getHost() + "/sessions";
        final ResponseEntity<Session> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                createHttpEntity(world.getUsername()),
                Session.class);
        if (responseEntity.getBody() != null) {
            Utils.json(responseEntity);

            Session body = responseEntity.getBody();

            Utils.json(body);
            world.setDeviceId(body.deviceId);
            world.setAuthToken(body.authToken);
            world.setSession(body);
        }
        world.setResponse(responseEntity);
    }

    @When("^I create a replist with name: \"([^\"]*)\"$")
    public void I_create_a_replist(String replistName) throws Throwable {
        String url = world.getHost() + "/replists";

        ReplistBean name = new ReplistBean();
        name.name = replistName;

        final ResponseEntity<Replist> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                createHttpEntityWithTokenAndBody(name),
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

    /// I retrieve all lists
    @When("^I retrieve all lists$")
    public void I_get_all() throws Throwable {
        String url = world.getHost() + "/replists";

        final ResponseEntity<List<Replist>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                createHttpEntityWithTokenAndBody(),
                new ParameterizedTypeReference<List<Replist>>() {
                });

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            Utils.json(responseEntity);
            List<Replist> list = responseEntity.getBody();
            for (int i = 0; i < list.size(); i++) {
                Replist replist = list.get(i);
                Utils.json(replist);
            }
        }
        world.setResponse(responseEntity);
    }

    @When("^I delete all lists$")
    public void iDeleteAllLists() throws Throwable {
        String url = world.getHost() + "/replists";

        final ResponseEntity<List<Replist>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET,
                        createHttpEntityWithTokenAndBody(), new ParameterizedTypeReference<List<Replist>>() {
                        });

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            List<Replist> list = responseEntity.getBody();
            log.debug("list : " + list);
            for (int i = 0; i < list.size(); i++) {
                Replist replist = list.get(i);
                Utils.json(replist);
                url = world.getHost() + "/replists/" + replist.id;
                restTemplate.exchange(url, HttpMethod.DELETE,
                        createHttpEntityWithTokenAndBody(), Void.class);
            }
        }
    }

    @When("^I search for the session by deviceId$")
    public void iSearchForTheSessionByDeviceId() throws Throwable {
        String url = world.getHost() + "/sessions?deviceId=" + world.getDeviceId();
        final ResponseEntity<Session> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                Session.class);
        if (responseEntity.getBody() != null) {
            Utils.json(responseEntity);
            Session body = responseEntity.getBody();
            Utils.json(body);
            world.setResponse(responseEntity);
        }
    }

    @When("^I retrieve a product$")
    public void iRetrieveAProduct() throws Throwable {
        String url = world.getHost() + "/replists/"+world.getReplist().id + "/articles/1";

        final ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET,
                createHttpEntityWithTokenAndBody(), String.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            log.debug("", responseEntity.getBody());

        }
        world.setResponse(responseEntity);
    }

    @When("^I retrieve all products$")
    public void iRetrieveAProducts() throws Throwable {
        String url = world.getHost() + "/replists/"+world.getReplist().id + "/articles";

        final ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET,
                createHttpEntityWithTokenAndBody(), String.class);

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
                createHttpEntityWithTokenAndBody(bean),
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

        Utils.json("WORLD:", world);
        log.debug("world = " + world);

        String url = world.getHost() + "/replists/"+world.getReplist().id+"/articles/"+pid;

        world.setArticleId(pid);

        ArticleBean articleBean = new ArticleBean();
        articleBean.articleNumber = pid;
        articleBean.brand = "Adidas";
        articleBean.colorway = "colorway";
        articleBean.gender = "kinder";
        articleBean.name = "PIPO";

        final ResponseEntity<ArticleBean> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET,
                createHttpEntityWithTokenAndBody(articleBean), ArticleBean.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            log.debug("", responseEntity.getBody());

            Utils.json("Response from replist service:", responseEntity.getBody());

            world.setSizesGrid(responseEntity.getBody());
        }
        world.setResponse(responseEntity);
    }


    @When("^I add a need for product: \"([^\"]*)\"$")
    public void iAddANeed(String pid) throws Throwable {
        Utils.json("WORLD:", world);
        log.debug("world = " + world);

        String url = world.getHost() + "/replists/"+world.getReplist().id+"/articles/"+pid;

        ArticleBean sizesGrid = world.getSizesGrid();
        ArticleItemBean articleItemBean = sizesGrid.sizes.get(0);
        articleItemBean.amount = 5L;

        final ResponseEntity<ArticleBean> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT,
                createHttpEntityWithTokenAndBody(sizesGrid), ArticleBean.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            log.debug("", responseEntity.getBody());

            Utils.json("Response from replist service:", responseEntity.getBody());

            world.setSizesGrid(responseEntity.getBody());
        }
        world.setResponse(responseEntity);

    }
}

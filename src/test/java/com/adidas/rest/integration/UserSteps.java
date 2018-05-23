package com.adidas.rest.integration;

import com.adidas.model.model.*;
import com.adidas.rest.integration.utils.Utils;
import com.adidas.sessions.dto.AppDataDTO;
import com.adidas.sessions.dto.SessionDTO;
import com.adidas.sessions.dto.UserDTO;
import com.google.common.collect.ImmutableList;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

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

    private SessionDTO createDefaultSessionDTO() {
        SessionDTO sessionDTO = new SessionDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setName(world.getUsername());
        userDTO.setStoreId(world.getStoreId());
        userDTO.setScale(world.getScale());
        userDTO.setRole(world.getRole());
        sessionDTO.setUser(userDTO);
        sessionDTO.setDeviceId(world.getDeviceId());
        List<AppDataDTO> appDataDTOList = createDefaultAppDataDTOList();
        sessionDTO.setAppData(appDataDTOList);
        return sessionDTO;
    }

    private List<AppDataDTO> createDefaultAppDataDTOList() {
        List<AppDataDTO> appDataDTOList = new ArrayList<>();
        AppDataDTO appDataDTO = new AppDataDTO();
        appDataDTO.setBundleId("com.adidas.sam.replenishment");
        Map<String, String> valuesMap = new LinkedHashMap<>();
        for (int i = 1; i <= 5; i++) {
            valuesMap.put(("key" + i), ("value" + i));
        }
        appDataDTO.setValues(valuesMap);
        appDataDTOList.add(appDataDTO);
        return appDataDTOList;
    }

    private HttpEntity createHttpEntity(final String username) {
        return new HttpEntity<>(createDefaultSessionDTO());
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
        final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                createHttpEntity(world.getUsername()),
                SessionDTO.class);
        if (responseEntity.getBody() != null) {
            Utils.json(responseEntity);

            SessionDTO body = responseEntity.getBody();

            Utils.json(body);
            world.setDeviceId(body.getDeviceId());
            world.setAuthToken(body.getAuthToken());
            world.setSession(body);
        }
        world.setResponse(responseEntity);
    }

    @When("^I create a (\\d+) sessions$")
    public void iCreateASession(int i) throws Throwable {
        String url = world.getHost() + "/sessions";

        for (int j = 0; j < i; j++) {

            final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    createHttpEntity(world.getUsername() + "-" + i),
                    SessionDTO.class);
            if (responseEntity.getBody() != null) {
                SessionDTO body = responseEntity.getBody();
                world.getResultList().add(body);
            }
            world.setResponse(responseEntity);

        }
    }

    // Assert 1000 sessions
//    @When("^I create a (\\d+) session$")
//    public void count_session(int i) throws Throwable {
//        String url = world.getHost() + "/sessions";
//
//
//        for (int j = 0; j < i; j++) {
//
//            final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    createHttpEntity(world.getUsername() + "-" + i),
//                    SessionDTO.class);
//            if (responseEntity.getBody() != null) {
//                SessionDTO body = responseEntity.getBody();
//                world.getResultList().add(body);
//            }
//            world.setResponse(responseEntity);
//
//        }
//    }

//
//    @When("^I create a (\\d+) session$")
//    public void createNSessions(int i) throws Throwable {
//        String url = world.getHost() + "/sessions";
//
//
//        for (int j = 0; j < i; j++) {
//
//            List<SessionDTO> resultList = world.getResultList();
//            for (int k = 0; k < resultList.size(); k++) {
//                SessionDTO sessionDTO = resultList.get(k);
//
//
//            final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    createHttpEntity(world.getUsername() + "-" + i),
//                    SessionDTO.class);
//            if (responseEntity.getBody() != null) {
//                SessionDTO body = responseEntity.getBody();
//                world.getResultList().add(body);
//            }
//            world.setResponse(responseEntity);
//
//            }
//
//        }
//    }

    @When("^I get a session$")
    public void I_get_a_session() throws Throwable {

        String url = world.getHost() + "/sessions/" + world.getDeviceId();
        final ResponseEntity<SessionDTO> responseEntity = restTemplate.getForEntity(url, SessionDTO.class);
        if (responseEntity.getBody() != null) {
            Utils.json(responseEntity);

            SessionDTO body = responseEntity.getBody();

            Utils.json(body);
            world.setDeviceId(body.getDeviceId());
            world.setAuthToken(body.getAuthToken());
            world.setSession(body);
        }
        world.setResponse(responseEntity);
    }

    @When("^I patch a session$")
    public void I_patch_a_session() throws Throwable {

        String url = world.getHost() + "/sessions/" + world.getDeviceId();

        AppDataDTO appDataDTO = new AppDataDTO();
        appDataDTO.setBundleId(world.getBundleId());

        Map<String, String> map = new HashMap<>();
        map.put("pushtoken", world.getPushToken());
        appDataDTO.setValues(map);

        SessionDTO sessionDTO = world.getSession();
        List<AppDataDTO> appDataList = sessionDTO.getAppData();
        appDataList.clear();
        appDataList.add(appDataDTO);

        HttpEntity entity = new HttpEntity<>(sessionDTO);

        final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PATCH,
                entity,
                SessionDTO.class);
        if (responseEntity.getBody() != null) {
            Utils.json(responseEntity);

            SessionDTO body = responseEntity.getBody();

            Utils.json(body);
            world.setDeviceId(body.getDeviceId());
            world.setAuthToken(body.getAuthToken());
            world.setSession(body);
        }
        world.setResponse(responseEntity);
    }

    @When("^I delete a session$")
    public void I_delete_a_session() throws Throwable {
        String url = world.getHost() + "/sessions/" + world.getDeviceId();
        restTemplate.delete(new URI(url));
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
            world.setCounter(list.size());
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
        final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                SessionDTO.class);
        if (responseEntity.getBody() != null) {
            Utils.json(responseEntity);
            SessionDTO body = responseEntity.getBody();
            Utils.json(body);
            world.setResponse(responseEntity);
        }
    }

    @When("^I retrieve a product$")
    public void iRetrieveAProduct() throws Throwable {
        String url = world.getHost() + "/replists/" + world.getReplist().id + "/articles/1";

        final ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET,
                createHttpEntityWithTokenAndBody(), String.class);

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
                url, HttpMethod.GET, createHttpEntityWithTokenAndBody(articleBean), ArticleBean.class);

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
                createHttpEntityWithTokenAndBody(sizesGrid), ArticleBean.class);

        if (responseEntity.getBody() != null) {
            log.debug("responseEntity = " + responseEntity);
            log.debug("", responseEntity.getBody());

            Utils.json("Response from replist service:", responseEntity.getBody());

            world.setSizesGrid(responseEntity.getBody());
        }
        world.setResponse(responseEntity);

    }

    @Then("^Assert (\\d+) sessions$")
    public void assertSessions(int arg0) throws Throwable {

//
//        String url = world.getHost() + "/sessions";
//        final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                SessionDTO.class);
//
//        Assert.assertEquals(arg0, );
    }

    @Given("^Server suffix: \"([^\"]*)\"$")
    public void serverSuffix(String suffix) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        world.setServerSuffix(suffix);
    }

    @Given("^Service: \"([^\"]*)\"$")
    public void service(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        world.setService(arg0);
    }
}

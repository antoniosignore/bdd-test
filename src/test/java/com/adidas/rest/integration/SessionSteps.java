package com.adidas.rest.integration;

import com.adidas.rest.integration.utils.Utils;
import com.adidas.sessions.dto.AppDataDTO;
import com.adidas.sessions.dto.SessionDTO;
import com.adidas.sessions.dto.UserDTO;
import com.adidas.sessions.dto.response.SessionsListResponse;
import com.google.common.collect.ImmutableList;
import cucumber.api.java.en.When;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@CucumberStepsDefinition
public class SessionSteps {
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

    @When("^I create a session$")
    public void I_create_a_session() {

        String url = world.getHost() + "/sessions";
        final ResponseEntity<SessionDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                createHttpEntity(world.getUsername()),
                SessionDTO.class);
        processResponse(responseEntity);
        world.setResponse(responseEntity);
    }

    private void processResponse(ResponseEntity<SessionDTO> responseEntity) {

        assert responseEntity != null;

        if (responseEntity.getBody() != null) {
            Utils.json("Response Entity :", responseEntity);
            Utils.json("Response Body   :", responseEntity.getBody());

            world.setResponse(responseEntity);

            SessionDTO body = responseEntity.getBody();
            assert body != null;
            assert world.getAuthToken() != null;
            assert world.getDeviceId() != null;

            world.setSession(body);
            world.setDeviceId(body.getDeviceId());
            world.setAuthToken(body.getAuthToken());
        }

    }

    @When("^I get a session by device id$")
    public void I_get_a_session() {
        log.debug("\n############### SessionSteps.I_get_a_session");
        String url = world.getHost() + "/sessions/" + world.getDeviceId();
        final ResponseEntity<SessionDTO> responseEntity = restTemplate.getForEntity(url, SessionDTO.class);
        processResponse(responseEntity);
    }

    @When("^I get a session by store id$")
    public void I_get_a_session_by_store() {
        log.debug("\n############### SessionSteps.I_get_a_session_by_store");
        String url = world.getHost() + "/sessions/store/" + world.getStoreId();
        final ResponseEntity<SessionsListResponse> responseEntity = restTemplate.getForEntity(url, SessionsListResponse.class);
        assert responseEntity != null;
        if (responseEntity.getBody() != null) {
            Utils.json("Response Entity :", responseEntity);
            Utils.json("Response Body   :", responseEntity.getBody());
            world.setResponse(responseEntity);
            SessionsListResponse body = responseEntity.getBody();
            assert body != null;
        }
    }

    @When("^I get a session by store id and role$")
    public void I_get_a_session_by_store_and_role() {
        log.debug("\n############### SessionSteps.I_get_a_session_by_store_and_role");
        String url = world.getHost() + "/sessions/store/" + world.getStoreId() + "/role/" + world.getRole().toString();
        final ResponseEntity<SessionsListResponse> responseEntity = restTemplate.getForEntity(url, SessionsListResponse.class);
        assert responseEntity != null;
        if (responseEntity.getBody() != null) {
            Utils.json("Response Entity :", responseEntity);
            Utils.json("Response Body   :", responseEntity.getBody());
            world.setResponse(responseEntity);
            SessionsListResponse body = responseEntity.getBody();
            assert body != null;
        }
    }

    @When("^I patch a session$")
    public void I_patch_a_session() {
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
                url, HttpMethod.PATCH, entity, SessionDTO.class);
        processResponse(responseEntity);
    }

    @When("^I delete a session$")
    public void I_delete_a_session() throws Throwable {
        String url = world.getHost() + "/sessions/" + world.getDeviceId();
        restTemplate.delete(new URI(url));
    }
}

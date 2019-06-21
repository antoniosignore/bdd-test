package com.bdd.rest.integration;

import com.bdd.configuration.EnvironmentConfiguration;
import com.bdd.rest.integration.utils.Utils;
import com.bdd.sessions.dto.Role;
import com.google.common.io.Files;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.nio.charset.Charset;
import java.util.UUID;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static net.javacrumbs.jsonunit.JsonAssert.whenIgnoringPaths;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
@CucumberStepsDefinition
@ToString
public class CommonSteps {

    @Autowired
    private World world;

    @Autowired
    EnvironmentConfiguration environmentConfiguration;

    @Given("^Host: \"([^\"]*)\"$")
    public void host(String host) {
        log.debug("host = " + host);
        world.setHost(host);
    }

    @Given("^Connect to: \"([^\"]*)\"$")
    public void connext(String host) {
        switch (host) {
            case "session":
                world.setHost(environmentConfiguration.getSessionUrl());
                break;
            case "replist":
                world.setHost(environmentConfiguration.getReplistUrl());
                break;
            case "size":
                world.setHost(environmentConfiguration.getSizeUrl());
                break;
            case "country":
                world.setHost(environmentConfiguration.getCountryUrl());
                break;
            case "article":
                world.setHost(environmentConfiguration.getArticleUrl());
                break;
            case "store":
                world.setHost(environmentConfiguration.getStoreUrl());
                break;
        }
        log.debug("--------------> HOST = " + world.getHost());
    }

    @Given("^Branch: \"([^\"]*)\"$")
    public void branch(String env) {
        world.setBranch(env);
    }

    @Given("^Username: \"([^\"]*)\"$")
    public void username(String username) {
        log.debug("username = " + username);
        world.setUsername(username +":" + UUID.randomUUID().toString());
    }

    @Given("^Password: \"([^\"]*)\"$")
    public void pwd(String pwd) {
        log.debug("pwd = " + pwd);
        world.setPassword(pwd);
    }

    @Then("^(?:I get|the user gets) a (.*) response$")
    public void I_get_a__response(final String statusCode) throws Throwable {
        final ResponseEntity response = world.getResponse();

        Utils.json("Response:", response.getBody());
        assertThat(response.getStatusCode(), is(HttpStatus.valueOf(statusCode)));
    }

    @Given("^StoreId: \"([^\"]*)\"$")
    public void storeid(String storeId) throws Throwable {
        world.setStoreId(storeId);
    }

    @Given("^DeviceId")
    public void deviceId() throws Throwable {
        world.setDeviceId(UUID.randomUUID().toString());
    }

    @Given("^Role: \"([^\"]*)\"$")
    public void role(String role) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        world.setRole(Role.valueOf(role));
    }

    @Given("^Scale: \"([^\"]*)\"$")
    public void scale(String scale) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        world.setScale(scale);
    }

    @Given("^BundleId: \"([^\"]*)\"$")
    public void bundleId(String bid) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        world.setBundleId(bid);
    }

    @Given("^PushToken: \"([^\"]*)\"$")
    public void pushToken(String pushtoken) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        world.setPushToken(pushtoken);
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

    @When("^I get by deviceId$")
    public void iGetByDeviceId() throws Throwable {
        // add the code to execute the GET / deviceId
    }


    @Then("^Expected response is the same as in file: \"([^\"]*)\"$")
    public void iAssertResponseWith(String expectedJsonFile) throws Throwable {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(expectedJsonFile).getFile());
        String json = Files.toString(file, Charset.forName("UTF-8"));
        log.debug("json = " + json);
        log.debug("world.getResponse().getBody() = " + world.getResponse().getBody());
        assertJsonEquals(json, Utils.jsonString(world.getResponse().getBody()));
    }

    @Then("^Expected response is the same as in file: \"([^\"]*)\" excluding \"([^\"]*)\"$")
    public void expectedResponseIsTheSameAsInFileExcluding(String expectedJsonFile, String arg1) throws Throwable {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(expectedJsonFile).getFile());
        String json = Files.toString(file, Charset.forName("UTF-8"));
        log.debug("json = " + json);
        log.debug("world.getResponse().getBody() = " + world.getResponse().getBody());

        final String[] split = arg1.split(",");

        log.debug("split = " + split);

        assertJsonEquals(json, Utils.jsonString(world.getResponse().getBody()),
                whenIgnoringPaths(split));
    }
}

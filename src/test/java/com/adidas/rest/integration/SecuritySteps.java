package com.adidas.rest.integration;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@CucumberStepsDefinition
public class SecuritySteps {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SecuritySteps.class);

    @Autowired
    private World world;

    @Given("^Host: \"([^\"]*)\"$")
    public void host(String host) {
        log.debug("host = " + host);
        world.setHost(host);
    }

    @Given("^Username: \"([^\"]*)\"$")
    public void username(String username) {
        log.debug("username = " + username);
        world.setUsername(username);
    }

    @Given("^Password: \"([^\"]*)\"$")
    public void pwd(String pwd) {
        log.debug("pwd = " + pwd);
        world.setPassword(pwd);
    }

}

package com.adidas.rest.integration;

import com.adidas.sessions.dto.Role;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@CucumberStepsDefinition
public class CommonSteps {

    @Autowired
    private World world;

    @Then("^(?:I get|the user gets) a (.*) response$")
    public void I_get_a__response(final String statusCode) throws Throwable {
        final ResponseEntity response = world.getResponse();
        assertThat(response.getStatusCode(), is(HttpStatus.valueOf(statusCode)));
    }

    @Given("^StoreId: \"([^\"]*)\"$")
    public void storeid(String storeId) throws Throwable {
        world.setStoreId(storeId);
    }

    @Given("^DeviceId: \"([^\"]*)\"$")
    public void deviceId(String storeId) throws Throwable {
        world.setDeviceId(storeId);
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


}

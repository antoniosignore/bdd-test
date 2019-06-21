package com.bdd.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Data
public class EnvironmentConfiguration {

    @Value("${services.salesforce-integration.url}")
    private String salesforceIntegration;


}

package com.adidas.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Data
public class EnvironmentConfiguration {

    @Value("${sam.services.session.url}")
    private String sessionUrl;

    @Value("${sam.services.replist.url}")
    private String replistUrl;

    @Value("${sam.services.size.url}")
    private String sizeUrl;

    @Value("${sam.services.country.url}")
    private String countryUrl;

    @Value("${sam.services.article.url}")
    private String articleUrl;



}

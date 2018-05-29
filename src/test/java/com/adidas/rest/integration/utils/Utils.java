package com.adidas.rest.integration.utils;

import com.adidas.rest.integration.World;
import com.google.common.collect.ImmutableList;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class Utils {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Utils.class);

    public static void json(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);
        log.debug("\n"+json);
    }

    public static void json(String title, Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);
        log.debug("\n"+title);
        log.debug("\n"+json);
    }

    public static HttpEntity createHttpEntityWithToken(Object body, World world) {
        final String authHeader = "Bearer " + world.getAuthToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", ImmutableList.of(authHeader));
        return new HttpEntity<>(body, httpHeaders);
    }

    public static HttpEntity createHttpEntityWithToken(World world) {
        final String authHeader = "Bearer " + world.getAuthToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", ImmutableList.of(authHeader));
        return new HttpEntity<>(httpHeaders);
    }

    public static <T> HttpEntity<T> createHttpEntity(final T entity) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return new HttpEntity<>(entity, httpHeaders);
    }

}

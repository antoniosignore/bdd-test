package com.adidas.rest.integration.utils;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

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
}

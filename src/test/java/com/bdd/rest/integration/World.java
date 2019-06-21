package com.bdd.rest.integration;

import com.bdd.model.model.ArticleBean;
import com.bdd.model.model.Country;
import com.bdd.model.model.Replist;
import com.bdd.sessions.dto.Role;
import com.bdd.sessions.dto.SessionDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("cucumber-glue")
public class World {

    private int counter;
    private String service;
    private String serverSuffix;
    private ResponseEntity<?> response;
    private SessionDTO session;
    private Replist replist;

    private String username;
    private String password;
    private String host;
    private String authToken;
    private String deviceId;
    private String storeId;
    private ArticleBean sizesGrid;
    private String articleId;
    private Role role;
    private String scale;
    private String pushToken;
    private String bundleId;

    private String branch;
    private String listId;
    private Country country;

}

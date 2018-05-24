package com.adidas.rest.integration;

import com.adidas.model.model.ArticleBean;
import com.adidas.model.model.Replist;
import com.adidas.sessions.dto.Role;
import com.adidas.sessions.dto.SessionDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    List<SessionDTO> resultList = new ArrayList<>();
    private String branch;
    private String listId;

    public String getAddress(){
        return "http://"+branch +"."+service +"."+serverSuffix;

    }
}

package com.adidas.rest.integration;

import com.adidas.model.model.ArticleBean;
import com.adidas.model.model.Replist;
import com.adidas.model.model.Session;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("cucumber-glue")
public class World {

    private ResponseEntity<?> response;
    private Session session;
    private Replist replist;

    private String username;
    private String password;
    private String host;
    private String authToken;
    private String deviceId;
    private ArticleBean sizesGrid;
    private String articleId;


    public void setPerformerCredentials(final String username, final String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
    }

    public void setPerformerCredentials(final String username, String host) {
        this.username = username;
        this.host = host;
    }

    public ResponseEntity<?> getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity<?> response) {
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public Replist getReplist() {
        return replist;
    }

    public void setReplist(Replist replist) {
        this.replist = replist;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setSizesGrid(ArticleBean sizesGrid) {
        this.sizesGrid = sizesGrid;
    }

    public ArticleBean getSizesGrid() {
        return sizesGrid;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }
}

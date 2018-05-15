package com.adidas.rest.integration;

import com.adidas.model.model.ArticleBean;
import com.adidas.model.model.Replist;
import com.adidas.sessions.dto.Role;
import com.adidas.sessions.dto.SessionDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("cucumber-glue")
public class World {

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

}

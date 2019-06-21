package com.bdd.sessions.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SessionDTO {

    private String deviceId;
    private String authToken;

    private UserDTO user;
    private List<AppDataDTO> appData = new ArrayList<>();

}

package com.bdd.sessions.dto.request;

import com.bdd.sessions.dto.UserDTO;
import lombok.Data;

@Data
public class SessionCreateDTO {

    private String deviceId;
    private UserDTO user;
}

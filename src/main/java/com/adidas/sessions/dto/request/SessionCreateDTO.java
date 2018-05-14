package com.adidas.sessions.dto.request;

import com.adidas.sessions.dto.UserDTO;
import lombok.Data;

@Data
public class SessionCreateDTO {

    private String deviceId;
    private UserDTO user;
}

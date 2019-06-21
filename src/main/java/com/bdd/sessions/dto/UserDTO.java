package com.bdd.sessions.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private Role role;
    private String scale;
    private String storeId;
    
}

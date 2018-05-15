package com.adidas.model.model;


import com.adidas.sessions.dto.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Session {

    public String deviceId;
    public String username;
    public String storeId;
    public Role role;
    public String pushToken;
    public String sizeScale;
    public String authToken;

}

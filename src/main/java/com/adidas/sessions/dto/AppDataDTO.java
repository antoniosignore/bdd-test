package com.adidas.sessions.dto;

import lombok.Data;

import java.util.Map;

@Data
public class AppDataDTO {

    private String bundleId;
    private Map<String, String> values;
}

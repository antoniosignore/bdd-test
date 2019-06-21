package com.bdd.representations.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class ApiError {
    private String[] fields;
    private String message;
    private String apiErrorCode;

}

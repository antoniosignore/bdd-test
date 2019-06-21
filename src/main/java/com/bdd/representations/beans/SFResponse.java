
package com.bdd.representations.beans;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SFResponse {

    public String statusCode;
    public Headers headers;
    public String body;

}

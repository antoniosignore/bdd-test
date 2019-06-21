package com.bdd.representations.beans;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class BillingAddress {

    private String city;
    private String country;
    private String geocodeAccuracy;
    private Double latitude;
    private Double longitude;
    private String postalCode;
    private String state;
    private String street;

}

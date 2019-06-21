package com.bdd.representations.beans;


import lombok.*;

@NoArgsConstructor
@Data
@ToString
@Builder
@AllArgsConstructor
public class Account implements SObject {

    private Attributes attributes;
    private String id;
    private Boolean isDeleted;
    private String masterRecordId;
    private String name;
    private String type;
    private String parentId;
    private String billingStreet;
    private String billingCity;
    private String billingState;
    private String billingPostalCode;
    private String billingCountry;
    private Double billingLatitude;
    private Double billingLongitude;

    /*
        Address	In the same building
        NearAddress	Near the address
        Block	Midway point of the block
        Street	Midway point of the street
        ExtendedZip	Center of the extended ZIP or postal code area
        Zip/Postal Code	Center of the ZIP or postal code area
        Neighborhood	Center of the neighborhood
        City	Center of the city
        County	Center of the county
        State/Province	Center of the state or province
        Unknown	No match for the address was found
     */
    private String billingGeocodeAccuracy;

    private BillingAddress billingAddress;
    private String shippingStreet;
    private String shippingCity;
    private String shippingState;
    private String shippingPostalCode;
    private String shippingCountry;
    private Double shippingLatitude;
    private Double shippingLongitude;
    private String shippingGeocodeAccuracy;
    private ShippingAddress shippingAddress;
    private String phone;
    private String website;
    private String photoUrl;
    private String industry;
    private Integer numberOfEmployees;
    private String description;
    private String ownerId;
    private String createdDate;
    private String createdById;
    private String lastModifiedDate;
    private String lastModifiedById;
    private String systemModstamp;
    private String lastActivityDate;
    private String lastViewedDate;
    private String lastReferencedDate;
    private String jigsaw;
    private String jigsawCompanyId;
    private String accountSource;
    private String sicDesc;
    private String sapIdentifier__c;

    // ManuallyCreated__c to ‘true’

    @Override
    public String toCsvHeader() {
        return "Name,SapIdentifier__c\n";
    }

    // todo remove hardcoded value for upsert SF test
    @Override
    public String toCsv() {
        return name+","+"1234";
    }
}

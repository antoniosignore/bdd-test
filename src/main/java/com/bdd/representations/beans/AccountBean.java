package com.bdd.representations.beans;


import lombok.*;

@NoArgsConstructor
@Data
@ToString
@Builder
@AllArgsConstructor
public class AccountBean implements SObject {


    private String name;

    private String sapIdentifier__c;

    // ManuallyCreated__c to ‘true’

    @Override
    public String toCsvHeader() {
        return "Name,SapIdentifier__c\n";
    }

    // todo remove hardcoded value for upsert SF test
    @Override
    public String toCsv() {
        return name+","+"1234343";
    }
}

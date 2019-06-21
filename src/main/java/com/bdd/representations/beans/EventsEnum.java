package com.bdd.representations.beans;

public enum EventsEnum {

    EVENTS_CUSTOMER_CREATED_EVENT("representation.events.concrete.AccountCreatedEvent"),
    EVENTS_CONTACT_CREATED_EVENT("representation.events.concrete.ContactCreatedEvent");

    public final String label;

    EventsEnum(String label) {
        this.label = label;
    }

}

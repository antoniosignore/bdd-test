package com.bdd.representations.beans;

public interface SObject {
    String toCsvHeader();

    String toCsv();
}

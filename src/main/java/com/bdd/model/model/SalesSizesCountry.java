package com.bdd.model.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesSizesCountry {

    private String scaleCode;
    private List<SalesSizeValue> salesSizes = new ArrayList<>();

}

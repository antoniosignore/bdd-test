package com.adidas.model.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SalesSizesMultiCountries {

    private List<SalesSizesCountry> salesSizesByCountry = new ArrayList<>();

    public void addSalesSizeCountry(SalesSizesCountry salesSizesCountry) {
        salesSizesByCountry.add(salesSizesCountry);
    }
}

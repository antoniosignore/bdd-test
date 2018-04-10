package com.adidas.model.model;

import lombok.Data;


@Data
public class ArticleItemBean {

    public Long amount;
    public Long inStock;
    public String size;

    public ArticleItemBean() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getInStock() {
        return inStock;
    }

    public void setInStock(Long inStock) {
        this.inStock = inStock;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

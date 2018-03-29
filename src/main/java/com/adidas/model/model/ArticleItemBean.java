package com.adidas.model.model;

import lombok.Data;


@Data
public class ArticleItemBean {

    public Long amount;
    public Long instock;
    public String size;

    public ArticleItemBean() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getInstock() {
        return instock;
    }

    public void setInstock(Long instock) {
        this.instock = instock;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

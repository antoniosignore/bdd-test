package com.adidas.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleItemBean {

    public Long amount;
    public Long inStock;
    public String size;

}

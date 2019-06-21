package com.bdd.model.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ArticleItem {

    public Long id;
    Long need;
    Long instock;
    String size;
    private Date createdAt;
    private Date updatedAt;
    public Article article;

}

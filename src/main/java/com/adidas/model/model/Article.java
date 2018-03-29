package com.adidas.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.TreeSet;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Article {

    public String articleId;
    public long createdDate;
    public long modifiedDate;
    public String title;
    public String colorway;
    public Set<ArticleItem> items = new TreeSet<>();
    public Replist replist;

}

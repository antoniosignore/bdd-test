package com.adidas.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArticleBean {

    public String id;
    public String articleNumber;
    public String modelNumber;
    public String name;
    public String brand;
    public String colorway;
    public String gender;
    public String division;
    public String marketingLine;
    public String imageUrl;
    public String sizePage;
    public Long frequency = 0L;
    public List<Image> images;
    public List<String> techSizes;
    public String createdAt;
    public String updatedAt;
    public List<ArticleItemBean> sizes;
    public Boolean fulfilled;
}

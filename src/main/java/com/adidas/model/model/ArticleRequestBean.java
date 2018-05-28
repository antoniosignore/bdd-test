package com.adidas.model.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArticleRequestBean {

    public String articleNumber;
    public List<Size> sizes = null;
    public String name;
    public String colorway;

}
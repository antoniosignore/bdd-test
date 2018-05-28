package com.adidas.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Replist {
    public String id;
    public String storeId;
    public String title;
    public String author;
    public String status;
    public String createdAt;
    public String updatedAt;
}

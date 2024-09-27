package com.raticuliin.cashflow.category.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Category {

    private Long id;
    private String categoryName;
    private String description;

}

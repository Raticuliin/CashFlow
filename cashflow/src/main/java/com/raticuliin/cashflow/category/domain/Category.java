package com.raticuliin.cashflow.category.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Category {

    private String id;
    private String name;
    private String description;

}

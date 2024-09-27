package com.raticuliin.cashflow.category.infra.out.postgres.mapper;


import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.category.infra.out.postgres.entity.CategoryEntity;

public class CategoryEntityMapper {

    public static Category entityToDomain(CategoryEntity entity) {

        return Category.builder()
                .id(entity.getId())
                .categoryName(entity.getCategoryName())
                .description(entity.getDescription())
                .build();

    }

    public static CategoryEntity domainToEntity(Category domain) {

        return CategoryEntity.builder()
                .id(domain.getId())
                .categoryName(domain.getCategoryName())
                .description(domain.getDescription())
                .build();

    }

}

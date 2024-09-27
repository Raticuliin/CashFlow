package com.raticuliin.cashflow.category.infra.in.rest.mapper;

import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.category.infra.in.rest.data.CategoryRequest;
import com.raticuliin.cashflow.category.infra.in.rest.data.CategoryResponse;

public class CategoryRestMapper {

    public static Category requestToDomain(CategoryRequest request) {

        return Category.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .build();

    }

    public static CategoryResponse domainToResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();

    }

}

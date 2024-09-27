package com.raticuliin.cashflow.category.app.in.usecase;

import com.raticuliin.cashflow.category.domain.Category;

public interface GetCategoryByIdUseCase {

    Category getCategoryById(Long id) throws Exception;

}

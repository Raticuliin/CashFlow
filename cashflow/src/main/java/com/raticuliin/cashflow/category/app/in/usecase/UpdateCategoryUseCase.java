package com.raticuliin.cashflow.category.app.in.usecase;

import com.raticuliin.cashflow.category.domain.Category;

public interface UpdateCategoryUseCase {

    Category updateCategory(Long id, Category category) throws Exception;

}

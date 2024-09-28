package com.raticuliin.cashflow.category.app.in.usecase;

import com.raticuliin.cashflow.category.domain.Category;

public interface CreateCategoryUseCase {

    Category createCategory(Category category) throws Exception;

}

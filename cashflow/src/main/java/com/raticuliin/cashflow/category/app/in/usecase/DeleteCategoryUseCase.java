package com.raticuliin.cashflow.category.app.in.usecase;

import com.raticuliin.cashflow.category.domain.Category;

public interface DeleteCategoryUseCase {

    Category deleteCategory(Long id) throws Exception;

}

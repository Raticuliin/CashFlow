package com.raticuliin.cashflow.category.app.out;

import com.raticuliin.cashflow.category.domain.Category;

import java.util.List;

public interface ICategoryRepository {

    Category createCategory(Category category);

    List<Category> getAllCategories();
    Category getCategoryById(Long id);

    Category updateCategory(Category category);

    void deleteCategory(Long id);

}

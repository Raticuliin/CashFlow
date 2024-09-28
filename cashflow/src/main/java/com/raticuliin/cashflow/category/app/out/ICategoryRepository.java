package com.raticuliin.cashflow.category.app.out;

import com.raticuliin.cashflow.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {

    boolean existsByName(String categoryName);

    Category createCategory(Category category);

    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);

    Category updateCategory(Category category);

    void deleteCategory(Long id);

}

package com.raticuliin.cashflow.category.app.in;

import com.raticuliin.cashflow.category.app.in.usecase.*;
import com.raticuliin.cashflow.category.domain.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements
        CreateCategoryUseCase,
        GetAllCategoriesUseCase,
        GetCategoryByIdUseCase,
        UpdateCategoryUseCase,
        DeleteCategoryUseCase {



    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public Category deleteCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }
}

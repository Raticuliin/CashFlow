package com.raticuliin.cashflow.category.app.in;

import com.raticuliin.cashflow.category.app.in.usecase.*;
import com.raticuliin.cashflow.category.app.out.ICategoryRepository;
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

    private final ICategoryRepository categoryRepository;


    @Override
    public Category createCategory(Category category) throws Exception {

        if (categoryRepository.existsByName(category.getCategoryName()))
            throw new Exception("Category name already exists");

        return categoryRepository.createCategory(category);
    }

    @Override
    public Category deleteCategory(Long id) throws Exception {
        Category category = getCategoryById(id);

        categoryRepository.deleteCategory(id);

        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        return categoryRepository.getCategoryById(id)
                .orElseThrow(() -> new Exception("Category not found with ID: " + id));
    }

    @Override
    public Category updateCategory(Long id, Category category) throws Exception {

        Category savedCategory = categoryRepository.getCategoryById(id)
                .orElseThrow(() -> new Exception(String.format("No bank found with ID: %d", id)));

        category.setId(id);

        if (category.getCategoryName() == null)
            category.setCategoryName(savedCategory.getCategoryName());

        if (category.getDescription() == null)
            category.setDescription(savedCategory.getDescription());

        if (categoryRepository.existsByName(category.getCategoryName()))
            throw new Exception("Category name already exists");

        return categoryRepository.updateCategory(category);
    }
}

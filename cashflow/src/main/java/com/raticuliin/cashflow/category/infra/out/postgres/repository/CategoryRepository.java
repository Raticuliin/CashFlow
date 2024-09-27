package com.raticuliin.cashflow.category.infra.out.postgres.repository;

import com.raticuliin.cashflow.category.app.out.ICategoryRepository;
import com.raticuliin.cashflow.category.domain.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CategoryRepository implements ICategoryRepository {
    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}

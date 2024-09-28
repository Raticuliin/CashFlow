package com.raticuliin.cashflow.category.infra.out.postgres.repository;

import com.raticuliin.cashflow.category.app.out.ICategoryRepository;
import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.category.infra.out.postgres.mapper.CategoryEntityMapper;
import com.raticuliin.cashflow.category.infra.out.postgres.repository.jpa.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepository implements ICategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public boolean existsByName(String categoryName) {
        return jpaCategoryRepository.existsByCategoryName(categoryName);
    }

    @Override
    public Category createCategory(Category category) {
        return CategoryEntityMapper.entityToDomain(
                jpaCategoryRepository.save(CategoryEntityMapper.domainToEntity(category)));
    }

    @Override
    public List<Category> getAllCategories() {
        return jpaCategoryRepository.findAll()
                .stream()
                .map(CategoryEntityMapper::entityToDomain)
                .toList();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return jpaCategoryRepository.findById(id)
                .map(CategoryEntityMapper::entityToDomain);
    }

    @Override
    public Category updateCategory(Category category) {
        return CategoryEntityMapper.entityToDomain(
                jpaCategoryRepository.save(CategoryEntityMapper.domainToEntity(category)));
    }

    @Override
    public void deleteCategory(Long id) {
        jpaCategoryRepository.deleteById(id);
    }
}

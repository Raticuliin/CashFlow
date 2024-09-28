package com.raticuliin.cashflow.category.infra.out.postgres.repository.jpa;

import com.raticuliin.cashflow.category.infra.out.postgres.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByCategoryName(String categoryName);

}

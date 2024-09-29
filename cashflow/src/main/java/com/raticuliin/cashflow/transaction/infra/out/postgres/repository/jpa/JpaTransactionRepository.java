package com.raticuliin.cashflow.transaction.infra.out.postgres.repository.jpa;

import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import com.raticuliin.cashflow.transaction.infra.out.postgres.entity.TransactionEntity;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(" SELECT t FROM TransactionEntity t " +
            " WHERE " +
            " (:transactionType IS NULL OR t.transactionType = :transactionType) AND " +
            " (:category IS NULL OR t.category = :category) AND " +
            " (:isRecurring IS NULL OR t.isRecurring = :isRecurring) AND " +
            " (:dateFrom IS NULL OR t.transactionDate <= :dateFrom) AND " +
            " (:dateTo IS NULL OR t.transactionDate >= :dateTo) ")
            List<TransactionEntity> findByFilter(
            @Nullable @Param("transactionType") TransactionType transactionType,
            @Nullable @Param("category") Category category,
            @Nullable @Param("isRecurring") Boolean isRecurring,
            @Nullable @Param("dateFrom") LocalDate dateFrom,
            @Nullable @Param("dateTo") LocalDate dateTo
    );

}

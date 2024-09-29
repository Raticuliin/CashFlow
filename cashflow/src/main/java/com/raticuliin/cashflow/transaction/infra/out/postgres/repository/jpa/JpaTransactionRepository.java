package com.raticuliin.cashflow.transaction.infra.out.postgres.repository.jpa;

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
            " (:categoryId IS NULL OR t.category = :categoryId) AND " +
            " (:isRecurring IS NULL OR t.isRecurring = :isRecurring) AND " +
            " (:dateFrom IS NULL OR t.transactionDate <= :dateFrom) AND " +
            " (:dateTo IS NULL OR t.transactionDate >= :dateTo) ")
            List<TransactionEntity> findByFilter(
            @Nullable @Param("transactionType") TransactionType transactionType,
            @Nullable @Param("categoryId") Long categoryId,
            @Nullable @Param("isRecurring") Boolean isRecurring,
            @Nullable @Param("dateFrom") LocalDate dateFrom,
            @Nullable @Param("dateTo") LocalDate dateTo
    );

}

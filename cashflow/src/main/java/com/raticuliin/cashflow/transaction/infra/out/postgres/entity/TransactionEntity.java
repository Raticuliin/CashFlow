package com.raticuliin.cashflow.transaction.infra.out.postgres.entity;

import com.raticuliin.cashflow.account.infra.out.postgres.entity.AccountEntity;
import com.raticuliin.cashflow.category.infra.out.postgres.entity.CategoryEntity;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "is_recurring")
    private Boolean isRecurring;

    @Column(name = "recurrence_date")
    private LocalDate recurrenceDate;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id")
    private AccountEntity accountFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id")
    private AccountEntity accountTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}

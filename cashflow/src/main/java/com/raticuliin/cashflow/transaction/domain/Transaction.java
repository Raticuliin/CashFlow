package com.raticuliin.cashflow.transaction.domain;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.category.domain.Category;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Transaction {

    private Long id;
    private String description;
    private BigDecimal value;
    private Boolean isRecurring;
    private LocalDate recurrenceDate;
    private LocalDateTime transactionDate;
    private Account account;
    private TransactionType transactionType;
    private Account accountTo;
    private Account accountFrom;
    private Category category;

}

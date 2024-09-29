package com.raticuliin.cashflow.transaction.infra.in.rest.data;

import com.raticuliin.cashflow.account.infra.in.rest.data.AccountResponse;
import com.raticuliin.cashflow.category.infra.in.rest.data.CategoryResponse;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long id;
    private String description;
    private BigDecimal value;
    private Boolean isRecurring;
    private LocalDate recurrenceDate;
    private LocalDateTime transactionDate;
    private AccountResponse account;
    private TransactionType transactionType;
    private AccountResponse accountFrom;
    private AccountResponse accountTo;
    private CategoryResponse category;

}

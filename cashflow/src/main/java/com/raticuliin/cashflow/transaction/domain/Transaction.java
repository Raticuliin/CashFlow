package com.raticuliin.cashflow.transaction.domain;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Transaction {

    private String id;
    private String description;
    private Double value;
    private Boolean periodic;
    private LocalDateTime date;
    private Account account;
    private Category category;
    private TransactionType transactionType;

}

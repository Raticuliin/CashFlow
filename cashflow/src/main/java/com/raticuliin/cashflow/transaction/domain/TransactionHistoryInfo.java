package com.raticuliin.cashflow.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class TransactionHistoryInfo {
    private Long transactionId;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private BigDecimal balance;
}

package com.raticuliin.cashflow.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TransactionHistory {
    private BigDecimal totalBalance;
    private List<TransactionHistoryInfo> transactionHistoryInfoList;
}

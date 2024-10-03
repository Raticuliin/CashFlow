package com.raticuliin.cashflow.transaction.domain;

import com.raticuliin.cashflow.account.domain.AccountResume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionHistoryByAccount {
    private AccountResume account;
    private TransactionHistory transactionHistory;
}

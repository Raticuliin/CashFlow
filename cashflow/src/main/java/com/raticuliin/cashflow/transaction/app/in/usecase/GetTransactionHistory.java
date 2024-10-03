package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.account.domain.AccountResume;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.domain.TransactionHistory;
import com.raticuliin.cashflow.transaction.domain.TransactionHistoryByAccount;

import java.math.BigDecimal;
import java.util.List;

public interface GetTransactionHistory {
    TransactionHistory getTransactionHistory(List<Transaction> transactionList, BigDecimal balance);
    TransactionHistoryByAccount getTransactionHistoryByAccount(List<Transaction> transactionList, AccountResume account);
}

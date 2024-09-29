package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.transaction.domain.Transaction;

import java.util.List;

public interface GetAllTransactionsUseCase {

    List<Transaction> getAllTransactions();

}

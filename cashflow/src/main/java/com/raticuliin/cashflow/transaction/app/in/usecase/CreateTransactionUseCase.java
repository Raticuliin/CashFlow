package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.transaction.domain.Transaction;

public interface CreateTransactionUseCase {

    Transaction createTransaction(Transaction transaction) throws Exception;

}

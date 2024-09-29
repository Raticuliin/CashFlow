package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.transaction.domain.Transaction;

public interface UpdateTransactionUseCase {

    Transaction updateTransaction(Long id, Transaction transaction) throws Exception;

}

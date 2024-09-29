package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.transaction.domain.Transaction;

public interface DeleteTransactionuseCase {

    Transaction deleteTransaction(Long id) throws Exception;

}

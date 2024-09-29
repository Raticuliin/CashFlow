package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.transaction.domain.Transaction;

public interface DeleteTransactionUseCase {

    Transaction deleteTransaction(Long id) throws Exception;

}

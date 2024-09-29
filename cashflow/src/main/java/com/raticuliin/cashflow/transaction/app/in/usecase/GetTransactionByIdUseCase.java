package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.transaction.domain.Transaction;

public interface GetTransactionByIdUseCase {

    Transaction getTransactionById(Long id) throws Exception;

}

package com.raticuliin.cashflow.transaction.app.in.usecase;

import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.domain.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface GetTransactionsByFilterUseCase {

    List<Transaction> getTransactionByFilter(
                                TransactionType transactionType,
                                Long categoryId,
                                Boolean isRecurring,
                                LocalDate dateFrom,
                                LocalDate dateTo) throws Exception;

}

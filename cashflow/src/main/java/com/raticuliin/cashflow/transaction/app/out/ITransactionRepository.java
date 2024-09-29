package com.raticuliin.cashflow.transaction.app.out;

import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.domain.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {

    Transaction createTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
    Optional<Transaction> getTransactionById(Long id);
    List<Transaction> getTransactionsByFilter(TransactionType transactionType,
                                              Category category,
                                              Boolean isRecurring,
                                              LocalDateTime dateFrom,
                                              LocalDateTime dateTo);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);

}

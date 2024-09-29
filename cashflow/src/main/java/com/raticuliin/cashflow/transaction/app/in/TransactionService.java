package com.raticuliin.cashflow.transaction.app.in;

import com.raticuliin.cashflow.account.app.in.AccountService;
import com.raticuliin.cashflow.category.app.in.CategoryService;
import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.transaction.app.in.usecase.*;
import com.raticuliin.cashflow.transaction.app.out.ITransactionRepository;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService implements
        CreateTransactionUseCase,
        GetAllTransactionsUseCase,
        GetTransactionByIdUseCase,
        GetTransactionsByFilterUseCase,
        UpdateTransactionUseCase,
        DeleteTransactionuseCase {

    private final ITransactionRepository transactionRepository;

    private final AccountService accountService;
    private final CategoryService categoryService;


    @Override
    public Transaction createTransaction(Transaction transaction) throws Exception {

        Category category = categoryService.getCategoryById(transaction.getCategory().getId());

        transaction.setCategory(category);

        if (transaction.getCategory() != null)
            transaction.setAccount(accountService.getAccountById(transaction.getAccount().getId()));

        if (transaction.getAccountFrom() != null)
            transaction.setAccountFrom(accountService.getAccountById(transaction.getAccountFrom().getId()));

        if (transaction.getAccountTo() != null)
            transaction.setAccountTo(accountService.getAccountById(transaction.getAccountTo().getId()));

        if (transaction.getTransactionType() != TransactionType.EXPENSE)
            transaction.setValue(transaction.getValue().negate());

        return transactionRepository.createTransaction(transaction);

    }

    @Override
    public List<Transaction> getAllTransactions() {

        return transactionRepository.getAllTransactions();

    }

    @Override
    public Transaction getTransactionById(Long id) throws Exception {

        return transactionRepository.getTransactionById(id)
                .orElseThrow(() -> new Exception(String.format("No transaction found with ID: %d", id)));

    }

    @Override
    public List<Transaction> getTransactionByFilter(TransactionType transactionType, Long categoryId, Boolean isRecurring, LocalDate dateFrom, LocalDate dateTo) throws Exception {
        return transactionRepository.getTransactionsByFilter(transactionType, categoryService.getCategoryById(categoryId), isRecurring, dateFrom, dateTo);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) throws Exception {

        Transaction savedTransaction = transactionRepository.getTransactionById(id)
                .orElseThrow(() -> new Exception(String.format("No transaction found with ID: %d", id)));

        transaction.setId(id);

        if (transaction.getDescription() == null)
            transaction.setDescription(savedTransaction.getDescription());

        if (transaction.getValue() == null)
            transaction.setValue(savedTransaction.getValue());

        if (transaction.getIsRecurring() == null)
            transaction.setIsRecurring(savedTransaction.getIsRecurring());

        if (transaction.getRecurrenceDate() == null)
            transaction.setRecurrenceDate(savedTransaction.getRecurrenceDate());

        if (transaction.getTransactionType() == null)
            transaction.setTransactionType(savedTransaction.getTransactionType());

        if (transaction.getAccount() == null)
            transaction.setAccount(savedTransaction.getAccount());
        else
            transaction.setAccount(accountService.getAccountById(transaction.getAccount().getId()));

        if (transaction.getTransactionType() == null)
            transaction.setTransactionType(savedTransaction.getTransactionType());

        if (transaction.getAccountFrom() == null)
            transaction.setAccountFrom(savedTransaction.getAccountFrom());
        else
            transaction.setAccountFrom(accountService.getAccountById(transaction.getAccountFrom().getId()));

        if (transaction.getAccountTo() == null)
            transaction.setAccountTo(savedTransaction.getAccountTo());
        else
            transaction.setAccountTo(accountService.getAccountById(transaction.getAccountTo().getId()));

        if (transaction.getCategory() == null)
            transaction.setCategory(savedTransaction.getCategory());
        else
            transaction.setCategory(categoryService.getCategoryById(transaction.getCategory().getId()));

        return transactionRepository.updateTransaction(transaction);

    }

    @Override
    public Transaction deleteTransaction(Long id) throws Exception {

        Transaction transaction = getTransactionById(id);

        transactionRepository.deleteTransaction(id);

        return transaction;

    }
}

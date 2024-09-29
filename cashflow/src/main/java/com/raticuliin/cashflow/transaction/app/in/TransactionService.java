package com.raticuliin.cashflow.transaction.app.in;

import com.raticuliin.cashflow.account.app.in.AccountService;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.category.app.in.CategoryService;
import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.transaction.app.in.usecase.*;
import com.raticuliin.cashflow.transaction.app.out.ITransactionRepository;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        checkRecurrence(transaction);

        if (transaction.getTransactionDate() == null)
            transaction.setTransactionDate(LocalDateTime.now());

        if (transaction.getTransactionType() == TransactionType.TRANSFER) {
            addTransferToAccounts(transaction);

            return transactionRepository.createTransaction(transaction);
        }

        addTransactionToAccount(transaction);

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
    public List<Transaction> getTransactionByFilter(
            TransactionType transactionType,
            Long categoryId,
            Boolean isRecurring,
            LocalDate dateFrom,
            LocalDate dateTo) throws Exception {

        return transactionRepository.getTransactionsByFilter(
                transactionType,
                categoryService.getCategoryById(categoryId),
                isRecurring,
                dateFrom==null?
                        null:
                        dateFrom.atStartOfDay(),
                dateTo==null?
                        null:
                        dateTo.atStartOfDay());
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) throws Exception {

        Transaction savedTransaction = transactionRepository.getTransactionById(id)
                .orElseThrow(() -> new Exception(String.format("No transaction found with ID: %d", id)));

        transaction.setId(id);

        // Campos no problematicos:
        if (transaction.getDescription() == null)
            transaction.setDescription(savedTransaction.getDescription());

        if (transaction.getIsRecurring() == null)
            transaction.setIsRecurring(savedTransaction.getIsRecurring());

        if (transaction.getRecurrenceDate() == null)
            transaction.setRecurrenceDate(savedTransaction.getRecurrenceDate());

        checkRecurrence(transaction);

        if (transaction.getTransactionDate() == null)
            transaction.setTransactionDate(savedTransaction.getTransactionDate());

        if (transaction.getCategory() == null)
            transaction.setCategory(savedTransaction.getCategory());

        if (transaction.getValue() == null)
            transaction.setValue(savedTransaction.getValue());

        // Campos problematicos
        if (transaction.getTransactionType() == null)
            transaction.setTransactionType(savedTransaction.getTransactionType());

        if (transaction.getTransactionType() == TransactionType.TRANSFER) {

            Account accountFrom;
            Account accountTo;

            // Obtengo los accounts que necesito
            if (transaction.getAccountFrom() == null)
                accountFrom = savedTransaction.getAccountFrom();
            else
                accountFrom = transaction.getAccountFrom();

            if (transaction.getAccountTo() == null)
                accountTo = savedTransaction.getAccountTo();
            else
                accountTo = transaction.getAccountTo();

            // Borro los registros anteriores
            removeTransferFromAccounts(transaction);

            // Setteo los nuevos account
            transaction.setAccountFrom(accountFrom);
            transaction.setAccountTo(accountTo);

            addTransferToAccounts(transaction);

        } else {
            Account account;

            if (transaction.getAccount() == null)
                account = savedTransaction.getAccount();
            else
                account = transaction.getAccount();

            removeTransactionFromAccount(transaction);

            transaction.setAccount(account);

            addTransactionToAccount(transaction);

        }

        return transactionRepository.updateTransaction(transaction);

    }

    @Override
    public Transaction deleteTransaction(Long id) throws Exception {

        Transaction transaction = getTransactionById(id);

        if (transaction.getTransactionType() == TransactionType.TRANSFER) {
            removeTransferFromAccounts(transaction);

            transactionRepository.deleteTransaction(id);

            return transaction;
        }

        removeTransactionFromAccount(transaction);

        transactionRepository.deleteTransaction(id);

        return transaction;

    }

    private void checkRecurrence(Transaction transaction) throws Exception {
        if (transaction.getIsRecurring())
            if (transaction.getRecurrenceDate() == null)
                throw new Exception("Recurrence date cannot be null");

        transaction.setRecurrenceDate(null);
    }

    private void addTransactionToAccount(Transaction transaction) throws Exception {

        if (transaction.getAccount() == null)
            throw new Exception("Account is required");

        Account account = accountService.getAccountById(transaction.getAccount().getId());

        if (transaction.getTransactionType() == TransactionType.EXPENSE &&
            transaction.getValue().compareTo(BigDecimal.ZERO) > 0)
            transaction.setValue(transaction.getValue().negate());

        account.setBalance(account.getBalance().add(transaction.getValue()));

        accountService.updateAccount(account.getId(), account);

        transaction.setAccount(account);
        transaction.setAccountFrom(null);
        transaction.setAccountTo(null);
    }

    private void addTransferToAccounts(Transaction transaction) throws Exception {

        if (transaction.getAccountFrom() == null)
            throw new Exception("Source account is required");

        if (transaction.getAccountTo() == null)
            throw new Exception("Destination account is required");

        Account accountFrom = accountService.getAccountById(transaction.getAccountFrom().getId());
        Account accountTo = accountService.getAccountById(transaction.getAccountTo().getId());

        accountFrom.setBalance(accountFrom.getBalance().subtract(transaction.getValue()));
        accountTo.setBalance(accountTo.getBalance().add(transaction.getValue()));

        accountFrom = accountService.updateAccount(accountFrom.getId(), accountFrom);
        accountTo = accountService.updateAccount(accountTo.getId(), accountTo);

        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);
        transaction.setAccount(null);
    }

    private void removeTransactionFromAccount(Transaction transaction) throws Exception {

        Account account = accountService.getAccountById(transaction.getAccount().getId());

        account.setBalance(account.getBalance().subtract(transaction.getValue()));

        accountService.updateAccount(account.getId(), account);
    }

    private void removeTransferFromAccounts(Transaction transaction) throws Exception {

        Account accountFrom = accountService.getAccountById(transaction.getAccountFrom().getId());
        Account accountTo = accountService.getAccountById(transaction.getAccountTo().getId());

        accountFrom.setBalance(accountFrom.getBalance().add(transaction.getValue()));
        accountTo.setBalance(accountTo.getBalance().subtract(transaction.getValue()));

        accountService.updateAccount(accountFrom.getId(), accountFrom);
        accountService.updateAccount(accountTo.getId(), accountTo);
    }
}

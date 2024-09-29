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

        if (transaction.getTransactionType() == TransactionType.TRANSFER) {
            return transactionRepository.createTransaction(manageCreateTransfer(transaction));
        }

        return transactionRepository.createTransaction(manageCreateIncomeExpense(transaction));

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

        if (transaction.getTransactionType() == TransactionType.TRANSFER) {
            manageDeleteTransfer(transaction);

            transactionRepository.deleteTransaction(id);

            return transaction;
        }

        manageDeleteIncomeExpense(transaction);

        transactionRepository.deleteTransaction(id);

        return transaction;

    }

    private Transaction manageCreateIncomeExpense(Transaction transaction) throws Exception {

        if (transaction.getAccount() == null)
            throw new Exception("Account is required");

        Account account = accountService.getAccountById(transaction.getAccount().getId());

        if (transaction.getTransactionType() == TransactionType.EXPENSE)
            transaction.setValue(transaction.getValue().negate());

        account.setBalance(account.getBalance().add(transaction.getValue()));

        accountService.updateAccount(account.getId(), account);

        transaction.setAccount(account);

        return transaction;

    }

    private Transaction manageCreateTransfer(Transaction transaction) throws Exception {

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

        return transaction;
    }

    private void manageDeleteIncomeExpense(Transaction transaction) throws Exception {

        Account account = accountService.getAccountById(transaction.getAccount().getId());

        account.setBalance(account.getBalance().subtract(transaction.getValue()));

        account = accountService.updateAccount(account.getId(), account);

        transaction.setAccountFrom(account);
    }

    private void manageDeleteTransfer(Transaction transaction) throws Exception {

        Account accountFrom = accountService.getAccountById(transaction.getAccountFrom().getId());
        Account accountTo = accountService.getAccountById(transaction.getAccountTo().getId());

        accountFrom.setBalance(accountFrom.getBalance().add(transaction.getValue()));
        accountTo.setBalance(accountTo.getBalance().subtract(transaction.getValue()));

        accountFrom = accountService.updateAccount(accountFrom.getId(), accountFrom);
        accountTo = accountService.updateAccount(accountTo.getId(), accountTo);

        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);
    }
}

package com.raticuliin.cashflow.account.app.in;

import com.raticuliin.cashflow.account.app.in.usecase.*;
import com.raticuliin.cashflow.account.app.out.IAccountRepository;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.bank.app.in.BankService;
import com.raticuliin.cashflow.bank.domain.Bank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService implements
        CreateAccountUseCase,
        GetAllAccountsUseCase,
        GetAccountByIdUseCase,
        GetAccountsByFilterUseCase,
        UpdateAccountUseCase {

    private final IAccountRepository accountRepository;

    private final BankService bankService;

    @Override
    public Account createAccount(Account account) throws Exception {

        Bank bank = bankService.getBankById(account.getBank().getId());

        account.setBank(bank);
        account.setDate(LocalDateTime.now());

        return accountRepository.createAccount(account);
    }

    @Override
    public List<Account> getAllAccounts() {

        return accountRepository.getAllAccounts();

    }

    @Override
    public Account getAccountById(Long id) throws Exception {

        Optional<Account> accountOptional = accountRepository.getAccountById(id);

        if (accountOptional.isEmpty()) {
            throw new Exception(String.format("No account found with ID: %d", id));
        }

        return accountOptional.get();
    }

    @Override
    public List<Account> getAccountsByFilter(String name, AccountType type, Long bankId) {
        return accountRepository.getAccountsByFilter(name, type, bankId);

    }

    @Override
    public Account updateAccount(Long id, Account account) throws Exception {

        Optional<Account> savedAccount = accountRepository.getAccountById(id);

        if (savedAccount.isEmpty())
            throw new Exception(String.format("No account found with ID: %d", id));


        account.setId(id);
        account.setDate(LocalDateTime.now());

        // Meto los valores si existen:
        if (account.getName() == null)
            account.setName(savedAccount.get().getName());
        if (account.getBalance() == null)
            account.setBalance(savedAccount.get().getBalance());
        if (account.getRevenue() == null)
            account.setRevenue(savedAccount.get().getRevenue());
        if (account.getBank() == null)
            account.setBank(savedAccount.get().getBank());
        if (account.getAccountType() == null)
            account.setAccountType(savedAccount.get().getAccountType());

        return accountRepository.updateAccount(account);
    }
}

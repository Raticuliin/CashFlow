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

@Service
@AllArgsConstructor
public class AccountService implements
        CreateAccountUseCase,
        GetAllAccountsUseCase,
        GetAccountByIdUseCase,
        GetAccountsByFilterUseCase,
        UpdateAccountUseCase,
        DeleteAccountUseCase{

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

        return accountRepository.getAccountById(id).orElseThrow(() -> new Exception(String.format("No account found with ID: %d", id)));

    }

    @Override
    public List<Account> getAccountsByFilter(String name, AccountType type, Long bankId) {
        return accountRepository.getAccountsByFilter(name, type, bankId);

    }

    @Override
    public Account updateAccount(Long id, Account account) throws Exception {

        Account savedAccount = accountRepository.getAccountById(id)
                .orElseThrow(() -> new Exception(String.format("No account found with ID: %d", id)));

        account.setId(id);
        account.setDate(LocalDateTime.now());

        if (account.getName() == null)
            account.setName(savedAccount.getName());
        if (account.getBalance() == null)
            account.setBalance(savedAccount.getBalance());
        if (account.getRevenue() == null)
            account.setRevenue(savedAccount.getRevenue());
        if (account.getBank() == null)
            account.setBank(savedAccount.getBank());
        if (account.getAccountType() == null)
            account.setAccountType(savedAccount.getAccountType());

        return accountRepository.updateAccount(account);
    }

    @Override
    public Account deleteAccount(Long id) throws Exception {

        Account account = getAccountById(id);

        accountRepository.deleteAccount(id);

        return account;
    }
}

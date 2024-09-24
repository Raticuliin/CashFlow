package com.raticuliin.cashflow.account.app.in;

import com.raticuliin.cashflow.account.app.in.usecase.CreateAccountUseCase;
import com.raticuliin.cashflow.account.app.in.usecase.GetAllAccountsUseCase;
import com.raticuliin.cashflow.account.app.out.IAccountRepository;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.bank.app.in.BankService;
import com.raticuliin.cashflow.bank.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService implements
        CreateAccountUseCase,
        GetAllAccountsUseCase {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private BankService bankService;

    @Override
    public Account createAccount(Account account) throws Exception {

        Bank bank = bankService.getBankById(account.getBank().getId());

        account.setBank(bank);
        account.setCreated(LocalDateTime.now());

        return accountRepository.createAccount(account);
    }

    @Override
    public List<Account> getAllAccounts() {

        return accountRepository.getAllAccounts();

    }
}

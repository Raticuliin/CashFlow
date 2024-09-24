package com.raticuliin.cashflow.account.app.in;

import com.raticuliin.cashflow.account.app.in.usecase.CreateAccountUseCase;
import com.raticuliin.cashflow.account.app.out.IAccountRepository;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.bank.app.in.BankService;
import com.raticuliin.cashflow.bank.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService implements
        CreateAccountUseCase {

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
}

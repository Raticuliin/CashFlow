package com.raticuliin.cashflow.account.app.out;

import com.raticuliin.cashflow.account.domain.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {

    Boolean existsById(Long id);
    Boolean existsByName(String name);

    Account createAccount(Account account);

    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Long id);
}

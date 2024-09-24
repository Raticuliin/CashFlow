package com.raticuliin.cashflow.account.app.out;

import com.raticuliin.cashflow.account.domain.Account;

import java.util.List;

public interface IAccountRepository {

    Account createAccount(Account account);

    List<Account> getAllAccounts();

}

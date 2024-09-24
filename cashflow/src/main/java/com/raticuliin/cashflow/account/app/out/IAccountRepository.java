package com.raticuliin.cashflow.account.app.out;

import com.raticuliin.cashflow.account.domain.Account;

public interface IAccountRepository {

    Account createAccount(Account account);

}

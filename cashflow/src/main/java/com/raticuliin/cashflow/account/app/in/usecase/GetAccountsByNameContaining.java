package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.Account;

import java.util.List;

public interface GetAccountsByNameContaining {

    List<Account> getAccountsByNameContaining(String name);

}

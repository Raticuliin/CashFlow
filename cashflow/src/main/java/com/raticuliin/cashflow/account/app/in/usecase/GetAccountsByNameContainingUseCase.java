package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.Account;

import java.util.List;

public interface GetAccountsByNameContainingUseCase {

    List<Account> getAccountsByNameContaining(String name);

}

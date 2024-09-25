package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.domain.AccountType;

import java.util.List;

public interface GetAccountsByFilterUseCase {

    List<Account> getAccountsByFilter(String name, AccountType type, Long bankId) throws Exception;

}

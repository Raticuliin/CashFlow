package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.Account;

public interface CreateAccountUseCase {

    Account createAccount(Account account) throws Exception;

}

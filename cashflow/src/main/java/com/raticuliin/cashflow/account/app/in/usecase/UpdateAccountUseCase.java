package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.Account;

public interface UpdateAccountUseCase {

    Account updateAccount(Long id, Account account) throws Exception;

}

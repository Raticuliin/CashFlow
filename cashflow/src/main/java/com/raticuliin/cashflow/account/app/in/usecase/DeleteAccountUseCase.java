package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.Account;

public interface DeleteAccountUseCase {

    Account deleteAccount(Long id) throws Exception;

}

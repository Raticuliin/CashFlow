package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.Account;

import java.math.BigDecimal;
import java.util.List;

public interface GetTotalBalanceUseCase {

    BigDecimal getTotalBalance(List<Account> accountList);

}

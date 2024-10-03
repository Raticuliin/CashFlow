package com.raticuliin.cashflow.account.app.in.usecase;

import com.raticuliin.cashflow.account.domain.AccountResume;

import java.math.BigDecimal;
import java.util.List;

public interface GetTotalBalanceUseCase {

    BigDecimal getTotalBalance(List<AccountResume> accountList);

}

package com.raticuliin.cashflow.bank.app.in.usecase;

import com.raticuliin.cashflow.bank.domain.Bank;

import java.util.List;

public interface GetBanksByFilterUseCase {

    List<Bank> getBanksByFilter(String name);

}

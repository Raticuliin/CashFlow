package com.raticuliin.cashflow.bank.app.in.usecase;

import com.raticuliin.cashflow.bank.domain.Bank;

import java.util.List;

public interface GetBankByNameContaining {

    List<Bank> getBankByNameContaining(String name);

}

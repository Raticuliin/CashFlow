package com.raticuliin.cashflow.bank.app.in.usecase;

import com.raticuliin.cashflow.bank.domain.Bank;

public interface CreateBankUseCase {

    Bank createBank(Bank bank) throws Exception;

}

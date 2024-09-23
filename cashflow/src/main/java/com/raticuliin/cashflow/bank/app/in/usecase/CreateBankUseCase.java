package com.raticuliin.cashflow.bank.app.in.usecase;

import com.raticuliin.cashflow.bank.domain.Bank;

public interface CreateBankUseCase {

    Bank create(Bank bank) throws Exception;

}

package com.raticuliin.cashflow.bank.app.in.usecase;

import com.raticuliin.cashflow.bank.domain.Bank;

public interface DeleteBankUseCase {
    Bank deleteBank(Long id) throws Exception;
}

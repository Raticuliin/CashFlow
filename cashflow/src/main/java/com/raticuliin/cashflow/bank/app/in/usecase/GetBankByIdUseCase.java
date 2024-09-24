package com.raticuliin.cashflow.bank.app.in.usecase;

import com.raticuliin.cashflow.bank.domain.Bank;

public interface GetBankByIdUseCase {
    Bank getBankById(Long id) throws Exception;
}

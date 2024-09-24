package com.raticuliin.cashflow.bank.app.in.usecase;

import com.raticuliin.cashflow.bank.domain.Bank;

public interface UpdateBankUseCase {
    Bank updateBank(Long id, Bank bank) throws Exception;
}

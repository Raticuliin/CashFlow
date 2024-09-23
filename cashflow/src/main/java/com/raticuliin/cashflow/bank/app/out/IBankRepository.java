package com.raticuliin.cashflow.bank.app.out;

import com.raticuliin.cashflow.bank.domain.Bank;

public interface IBankRepository {

    Bank createBank(Bank bank);
    boolean existsByName(String name);

}

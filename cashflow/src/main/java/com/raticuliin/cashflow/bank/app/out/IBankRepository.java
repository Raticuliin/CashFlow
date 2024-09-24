package com.raticuliin.cashflow.bank.app.out;

import com.raticuliin.cashflow.bank.domain.Bank;

import java.util.List;
import java.util.Optional;

public interface IBankRepository {

    Bank createBank(Bank bank);
    boolean existsByName(String name);

    List<Bank> getAllBanks();
    Optional<Bank> getBankById(long id);
    List<Bank> getBanksByNameContaining(String name);

}

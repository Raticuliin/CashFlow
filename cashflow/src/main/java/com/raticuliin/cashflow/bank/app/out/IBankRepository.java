package com.raticuliin.cashflow.bank.app.out;

import com.raticuliin.cashflow.bank.domain.Bank;

import java.util.List;
import java.util.Optional;

public interface IBankRepository {

    Bank createBank(Bank bank);

    List<Bank> getAllBanks();
    Optional<Bank> getBankById(long id);
    List<Bank> getBanksByNameContaining(String name);

    Bank updateBank(Bank bank);

    boolean existsByName(String name);
    boolean existsById(long id);
}

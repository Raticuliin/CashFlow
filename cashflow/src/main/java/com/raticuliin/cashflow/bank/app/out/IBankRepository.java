package com.raticuliin.cashflow.bank.app.out;

import com.raticuliin.cashflow.bank.domain.Bank;

import java.util.List;
import java.util.Optional;

public interface IBankRepository {

    Bank createBank(Bank bank);

    List<Bank> getAllBanks();
    Optional<Bank> getBankById(Long id);
    List<Bank> getBanksByFilter(String name);

    Bank updateBank(Bank bank);

    void deleteBank(Long id);

    boolean existsByName(String name);
    boolean existsById(Long id);

}

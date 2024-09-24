package com.raticuliin.cashflow.bank.app.in;

import com.raticuliin.cashflow.bank.app.in.usecase.*;
import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.bank.infra.out.postgres.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService implements
        CreateBankUseCase,
        GetAllBanksUseCase,
        GetBankByIdUseCase,
        GetBankByNameContaining,
        UpdateBankUseCase,
        DeleteBankUseCase {

    @Autowired
    BankRepository bankRepository;

    @Override
    public Bank createBank(Bank bank) throws Exception {

        if (bankRepository.existsByName(bank.getName())) {
            throw new Exception("Bank name already exists");
        }

        return bankRepository.createBank(bank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.getAllBanks();
    }

    @Override
    public Bank getBankById(long id) throws Exception{

        Optional<Bank> bankOptional = bankRepository.getBankById(id);

        if (bankOptional.isEmpty())
            throw new Exception(String.format("No bank found with ID: %d", id));

        return bankOptional.get();
    }

    @Override
    public List<Bank> getBankByNameContaining(String name) {
        return bankRepository.getBanksByNameContaining(name);
    }

    @Override
    public Bank updateBank(Bank bank) throws Exception {



        if (!bankRepository.existsById(bank.getId())) {
            throw new Exception(String.format("No bank found with ID: %d", bank.getId()));
        }

        if (bankRepository.existsByName(bank.getName())) {
            throw new Exception("Bank name already exists");
        }

        return bankRepository.updateBank(bank);
    }

    @Override
    public Bank deleteBank(long id) throws Exception {

        Bank bank = getBankById(id);

        bankRepository.deleteBank(id);

        return bank;
    }
}

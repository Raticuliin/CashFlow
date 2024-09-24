package com.raticuliin.cashflow.bank.app.in;

import com.raticuliin.cashflow.bank.app.in.usecase.CreateBankUseCase;
import com.raticuliin.cashflow.bank.app.in.usecase.GetAllBanksUseCase;
import com.raticuliin.cashflow.bank.app.in.usecase.GetBankByIdUseCase;
import com.raticuliin.cashflow.bank.app.in.usecase.GetBankByNameContaining;
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
        GetBankByNameContaining {

    @Autowired
    BankRepository bankRepository;

    @Override
    public Bank create(Bank bank) throws Exception {

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
            throw new Exception("No bank found with that ID");

        return bankOptional.get();
    }

    @Override
    public List<Bank> getBankByNameContaining(String name) {
        return bankRepository.getBanksByNameContaining(name);
    }
}

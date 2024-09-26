package com.raticuliin.cashflow.bank.app.in;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.bank.app.in.usecase.*;
import com.raticuliin.cashflow.bank.app.out.IBankRepository;
import com.raticuliin.cashflow.bank.domain.Bank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankService implements
        CreateBankUseCase,
        GetAllBanksUseCase,
        GetBankByIdUseCase,
        GetBanksByFilterUseCase,
        UpdateBankUseCase,
        DeleteBankUseCase {

    private final IBankRepository bankRepository;

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
    public Bank getBankById(Long id) throws Exception{

        Optional<Bank> bankOptional = bankRepository.getBankById(id);

        if (bankOptional.isEmpty())
            throw new Exception(String.format("No bank found with ID: %d", id));

        return bankOptional.get();
    }

    @Override
    public List<Bank> getBanksByFilter(String name) {
        return bankRepository.getBanksByFilter(name);
    }

    @Override
    public Bank updateBank(Long id, Bank bank) throws Exception {

        Optional<Bank> savedBank = bankRepository.getBankById(id);

        if (savedBank.isEmpty())
            throw new Exception(String.format("No bank found with ID: %d", id));


        bank.setId(id);

        if (bank.getName() == null)
            bank.setName(savedBank.get().getName());

        return bankRepository.updateBank(bank);
    }

    @Override
    public Bank deleteBank(Long id) throws Exception {

        Bank bank = getBankById(id);

        bankRepository.deleteBank(id);

        return bank;
    }
}

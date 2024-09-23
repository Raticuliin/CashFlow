package com.raticuliin.cashflow.bank.app.in;

import com.raticuliin.cashflow.bank.app.in.usecase.CreateBankUseCase;
import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.bank.infra.out.postgres.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService implements
        CreateBankUseCase {

    @Autowired
    BankRepository bankRepository;

    @Override
    public Bank create(Bank bank) throws Exception {

        if (bankRepository.existsByName(bank.getName())) {
            throw new Exception("Bank name already exists");
        }

        return bankRepository.createBank(bank);
    }
}

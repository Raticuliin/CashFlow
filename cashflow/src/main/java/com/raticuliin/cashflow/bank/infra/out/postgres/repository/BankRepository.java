package com.raticuliin.cashflow.bank.infra.out.postgres.repository;

import com.raticuliin.cashflow.bank.app.out.IBankRepository;
import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.bank.infra.out.postgres.mapper.BankEntityMapper;
import com.raticuliin.cashflow.bank.infra.out.postgres.repository.jpa.JpaBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankRepository implements IBankRepository {

    @Autowired
    JpaBankRepository jpaBankRepository;

    @Override
    public Bank createBank(Bank bank) {
        return BankEntityMapper.entityToDomain(
                jpaBankRepository.save(BankEntityMapper.domainToEntity(bank))
        );
    }

    @Override
    public boolean existsByName(String name) {
        return jpaBankRepository.existsByName(name);
    }
}

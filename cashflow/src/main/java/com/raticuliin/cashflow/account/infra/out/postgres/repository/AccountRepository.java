package com.raticuliin.cashflow.account.infra.out.postgres.repository;

import com.raticuliin.cashflow.account.app.out.IAccountRepository;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.infra.out.postgres.mapper.AccountEntityMapper;
import com.raticuliin.cashflow.account.infra.out.postgres.repository.jpa.JpaAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository implements IAccountRepository {

    @Autowired
    JpaAccountRepository jpaAccountRepository;

    @Override
    public Boolean existsByName(String name) {
        return jpaAccountRepository.existsByName(name);
    }

    @Override
    public Boolean existsById(Long id) {
        return jpaAccountRepository.existsById(id);
    }

    @Override
    public Account createAccount(Account account) {
        return AccountEntityMapper.entityToDomain(jpaAccountRepository.save(AccountEntityMapper.domainToEntity(account)));
    }

    @Override
    public List<Account> getAllAccounts() {
        return jpaAccountRepository.findAll()
                .stream()
                .map(AccountEntityMapper::entityToDomain)
                .toList();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return jpaAccountRepository.findById(id)
                .map(AccountEntityMapper::entityToDomain);
    }
}

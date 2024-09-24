package com.raticuliin.cashflow.account.infra.out.postgres.repository;

import com.raticuliin.cashflow.account.app.out.IAccountRepository;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.infra.out.postgres.mapper.AccountEntityMapper;
import com.raticuliin.cashflow.account.infra.out.postgres.repository.jpa.JpaAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository implements IAccountRepository {

    @Autowired
    JpaAccountRepository jpaAccountRepository;

    @Override
    public Account createAccount(Account account) {
        return AccountEntityMapper.entityToDomain(jpaAccountRepository.save(AccountEntityMapper.domainToEntity(account)));
    }
}

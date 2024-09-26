package com.raticuliin.cashflow.account.infra.out.postgres.repository;

import com.raticuliin.cashflow.account.app.out.IAccountRepository;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.account.infra.out.postgres.mapper.AccountEntityMapper;
import com.raticuliin.cashflow.account.infra.out.postgres.repository.jpa.JpaAccountRepository;
import com.raticuliin.cashflow.bank.infra.out.postgres.mapper.BankEntityMapper;
import com.raticuliin.cashflow.bank.infra.out.postgres.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountRepository implements IAccountRepository {

    private final JpaAccountRepository jpaAccountRepository;
    private final BankRepository bankRepository;

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

    @Override
    public List<Account> getAccountsByFilter(String name, AccountType type, Long bankId) {

        return jpaAccountRepository.findByFilter(
                    name,
                    type,
                    bankId != null ?
                            bankRepository.getBankById(bankId)
                                .map(BankEntityMapper::domainToEntity)
                                .orElse(null)
                        : null)
                .stream()
                .map(AccountEntityMapper::entityToDomain)
                .toList();

    }

    @Override
    public Account updateAccount(Account account) {

        return AccountEntityMapper.entityToDomain(
                jpaAccountRepository.save(
                        AccountEntityMapper.domainToEntity(account)));

    }
}

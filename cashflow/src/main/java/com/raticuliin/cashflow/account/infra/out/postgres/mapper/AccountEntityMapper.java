package com.raticuliin.cashflow.account.infra.out.postgres.mapper;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.infra.out.postgres.entity.AccountEntity;
import com.raticuliin.cashflow.bank.infra.in.rest.mapper.BankMapper;
import com.raticuliin.cashflow.bank.infra.out.postgres.mapper.BankEntityMapper;

public class AccountEntityMapper {

    public static Account entityToDomain(AccountEntity accountEntity) {

        return Account.builder()
                .id(accountEntity.getId())
                .name(accountEntity.getName())
                .balance(accountEntity.getBalance())
                .revenue(accountEntity.getRevenue())
                .created(accountEntity.getCreated())
                .bank(
                        BankEntityMapper.entityToDomain(
                                accountEntity.getBankEntity()))
                .accountType(accountEntity.getAccountType())
                .build();

    }

    public static AccountEntity domainToEntity(Account account) {

        return AccountEntity.builder()
                .id(account.getId())
                .name(account.getName())
                .balance(account.getBalance())
                .revenue(account.getRevenue())
                .created(account.getCreated())
                .bankEntity(
                        BankEntityMapper.domainToEntity(
                                account.getBank()))
                .accountType(account.getAccountType())
                .build();

    }

}

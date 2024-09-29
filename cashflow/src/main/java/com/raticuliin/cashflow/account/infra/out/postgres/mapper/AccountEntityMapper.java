package com.raticuliin.cashflow.account.infra.out.postgres.mapper;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.infra.out.postgres.entity.AccountEntity;
import com.raticuliin.cashflow.bank.infra.out.postgres.mapper.BankEntityMapper;
import com.raticuliin.cashflow.transaction.infra.out.postgres.mapper.TransactionEntityMapper;

public class AccountEntityMapper {

    public static Account entityToDomain(AccountEntity accountEntity) {

        return Account.builder()
                .id(accountEntity.getId())
                .name(accountEntity.getName())
                .balance(accountEntity.getBalance())
                .revenue(accountEntity.getRevenue())
                .date(accountEntity.getDate())
                .bank(
                        BankEntityMapper.entityToDomain(
                                accountEntity.getBankEntity()))
                .transactions(
                        accountEntity.getTransactions() == null ?
                                null :
                                accountEntity.getTransactions()
                                    .stream()
                                    .map(
                                        TransactionEntityMapper::entityToDomain)
                                    .toList()
                )
                .incomingTransfers(
                        accountEntity.getIncomingTransfers() == null ?
                                null :
                                accountEntity.getIncomingTransfers()
                                        .stream()
                                        .map(
                                                TransactionEntityMapper::entityToDomain)
                                        .toList()
                )
                .outgoingTransfers(
                        accountEntity.getOutgointTransfers() == null ?
                                null :
                                accountEntity.getOutgointTransfers()
                                        .stream()
                                        .map(
                                                TransactionEntityMapper::entityToDomain)
                                        .toList()
                )
                .accountType(accountEntity.getAccountType())
                .build();

    }

    public static AccountEntity domainToEntity(Account account) {

        return AccountEntity.builder()
                .id(account.getId())
                .name(account.getName())
                .balance(account.getBalance())
                .revenue(account.getRevenue())
                .date(account.getDate())
                .bankEntity(
                        BankEntityMapper.domainToEntity(
                                account.getBank()))
                .transactions(
                        account.getTransactions() == null ?
                                null :
                                account.getTransactions()
                                        .stream()
                                        .map(
                                                TransactionEntityMapper::domainToEntity)
                                        .toList()
                )
                .incomingTransfers(
                        account.getIncomingTransfers() == null ?
                                null :
                                account.getIncomingTransfers()
                                        .stream()
                                        .map(
                                                TransactionEntityMapper::domainToEntity)
                                        .toList()
                )
                .outgointTransfers(
                        account.getOutgoingTransfers() == null ?
                                null :
                                account.getOutgoingTransfers()
                                        .stream()
                                        .map(
                                                TransactionEntityMapper::domainToEntity)
                                        .toList()
                )
                .accountType(account.getAccountType())
                .build();

    }

    public static Account entityToDomainWithoutTransactions(AccountEntity accountEntity) {

        return Account.builder()
                .id(accountEntity.getId())
                .name(accountEntity.getName())
                .balance(accountEntity.getBalance())
                .revenue(accountEntity.getRevenue())
                .date(accountEntity.getDate())
                .bank(
                        BankEntityMapper.entityToDomain(
                                accountEntity.getBankEntity()))
                .accountType(accountEntity.getAccountType())
                .build();

    }

    public static AccountEntity domainToEntityWithoutTransactions(Account account) {

        return AccountEntity.builder()
                .id(account.getId())
                .name(account.getName())
                .balance(account.getBalance())
                .revenue(account.getRevenue())
                .date(account.getDate())
                .bankEntity(
                        BankEntityMapper.domainToEntity(
                                account.getBank()))
                .accountType(account.getAccountType())
                .build();

    }

}

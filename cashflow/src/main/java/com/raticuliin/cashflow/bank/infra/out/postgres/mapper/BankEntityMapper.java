package com.raticuliin.cashflow.bank.infra.out.postgres.mapper;

import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.bank.infra.out.postgres.entity.BankEntity;

public class BankEntityMapper {

    public static Bank entityToDomain(BankEntity bankEntity) {

        return Bank.builder()
                .id(bankEntity.getId())
                .name(bankEntity.getName())
                .build();

    }

    public static BankEntity domainToEntity(Bank bank) {

        return BankEntity.builder()
                .id(bank.getId())
                .name(bank.getName())
                .build();

    }
}

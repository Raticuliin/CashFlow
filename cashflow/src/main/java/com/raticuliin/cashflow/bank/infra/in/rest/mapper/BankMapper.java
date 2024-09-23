package com.raticuliin.cashflow.bank.infra.in.rest.mapper;

import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.bank.infra.in.rest.data.BankResponse;
import com.raticuliin.cashflow.bank.infra.in.rest.data.CreateBankRequest;

public class BankMapper {

    public static Bank createBankRequestToDomain(CreateBankRequest createBankRequest) {

        return Bank.builder()
                .name(createBankRequest.getName())
                .build();

    }

    public static BankResponse domainToBankResponse(Bank bank) {

        return BankResponse.builder()
                .id(bank.getId())
                .name(bank.getName())
                .build();

    }

}

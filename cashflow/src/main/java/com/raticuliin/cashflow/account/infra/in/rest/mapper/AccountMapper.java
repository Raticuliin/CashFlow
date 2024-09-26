package com.raticuliin.cashflow.account.infra.in.rest.mapper;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.infra.in.rest.data.AccountRequest;
import com.raticuliin.cashflow.account.infra.in.rest.data.AccountResponse;
import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.bank.infra.in.rest.mapper.BankMapper;

public class AccountMapper {

    public static Account requestToDomain(AccountRequest request) {

        return Account.builder()
                .name(request.getName())
                .balance(request.getBalance())
                .revenue(request.getRevenue())
                .bank(
                        Bank.builder()
                                .id(request.getBankId())
                                .build()
                )
                .accountType(request.getAccountType())
                .build();

    }

    public static AccountResponse domainToResponse(Account account) {

        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .balance(account.getBalance())
                .revenue(account.getRevenue())
                .date(account.getDate())
                .bank(
                        BankMapper.domainToBankResponse(account.getBank())
                )
                .accountType(account.getAccountType())
                .build();

    }

}

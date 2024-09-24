package com.raticuliin.cashflow.bank.domain;

import com.raticuliin.cashflow.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Bank {

    private Long id;
    private String name;
    private List<Account> accounts;

}

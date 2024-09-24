package com.raticuliin.cashflow.account.infra.in.rest.data;

import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.bank.domain.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private String name;
    private Double balance;
    private Double revenue;
    private Long bankId;
    private AccountType accountType;

}

package com.raticuliin.cashflow.account.domain;

import com.raticuliin.cashflow.bank.domain.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Account {

    private Long id;
    private String name;
    private BigDecimal balance;
    private BigDecimal revenue;
    private LocalDateTime date;
    private Bank bank;
    private AccountType accountType;


}

package com.raticuliin.cashflow.account.infra.in.rest.data;

import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.bank.infra.in.rest.data.BankResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long id;
    private String name;
    private BigDecimal balance;
    private BigDecimal revenue;
    private LocalDateTime date;
    private BankResponse bank;
    private AccountType accountType;

}

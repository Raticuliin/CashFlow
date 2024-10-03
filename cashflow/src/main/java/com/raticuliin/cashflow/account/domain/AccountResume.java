package com.raticuliin.cashflow.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class AccountResume {
    private Long id;
    private String name;
    private BigDecimal balance;
}

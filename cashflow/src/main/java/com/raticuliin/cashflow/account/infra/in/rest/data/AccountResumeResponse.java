package com.raticuliin.cashflow.account.infra.in.rest.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResumeResponse {

    private Long id;
    private String name;
    private BigDecimal balance;

}

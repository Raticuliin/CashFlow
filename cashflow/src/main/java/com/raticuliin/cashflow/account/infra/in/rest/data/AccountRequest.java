package com.raticuliin.cashflow.account.infra.in.rest.data;

import com.raticuliin.cashflow.account.domain.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NonNull
    private String name;

    private BigDecimal balance;

    private BigDecimal revenue;

    @NonNull
    private Long bankId;

    @NonNull
    private AccountType accountType;

}

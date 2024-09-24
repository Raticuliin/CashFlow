package com.raticuliin.cashflow.account.infra.in.rest.data;

import com.raticuliin.cashflow.account.domain.AccountType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NonNull
    private String name;

    private Double balance;

    private Double revenue;

    @NonNull
    private Long bankId;

    @NonNull
    private AccountType accountType;

}

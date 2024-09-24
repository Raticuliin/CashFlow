package com.raticuliin.cashflow.bank.infra.in.rest.data;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRequest {
    @NonNull
    private String name;
}

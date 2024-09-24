package com.raticuliin.cashflow.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Bank {

    private Long id;
    private String name;

}

package com.raticuliin.cashflow.account.infra.in.rest.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResumeListResponse {

    private BigDecimal totalBalance;
    private List<AccountResponse> accountList;

}

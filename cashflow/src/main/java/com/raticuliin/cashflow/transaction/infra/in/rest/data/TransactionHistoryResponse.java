package com.raticuliin.cashflow.transaction.infra.in.rest.data;

import com.raticuliin.cashflow.transaction.domain.TransactionHistory;
import com.raticuliin.cashflow.transaction.domain.TransactionHistoryByAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryResponse {

    private TransactionHistory totalHistory;
    private List<TransactionHistoryByAccount> accountHistoryList;

}

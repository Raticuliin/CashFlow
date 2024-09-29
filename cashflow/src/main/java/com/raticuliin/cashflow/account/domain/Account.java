package com.raticuliin.cashflow.account.domain;

import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    private List<Transaction> transactions;
    private List<Transaction> incomingTransfers;
    private List<Transaction> outgoingTransfers;


}

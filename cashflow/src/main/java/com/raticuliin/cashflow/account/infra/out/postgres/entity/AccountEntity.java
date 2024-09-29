package com.raticuliin.cashflow.account.infra.out.postgres.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.bank.infra.out.postgres.entity.BankEntity;
import com.raticuliin.cashflow.transaction.infra.out.postgres.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name")
    private String name;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "revenue")
    private BigDecimal revenue;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "account_type")
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bankEntity;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<TransactionEntity> transactions;

    @OneToMany(
            mappedBy = "accountFrom",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<TransactionEntity> outgointTransfers;

    @OneToMany(
            mappedBy = "accountTo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<TransactionEntity> incomingTransfers;

}

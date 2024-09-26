package com.raticuliin.cashflow.account.infra.out.postgres.entity;

import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.bank.infra.out.postgres.entity.BankEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Double balance;

    @Column(name = "revenue")
    private Double revenue;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "account_type")
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bankEntity;


}

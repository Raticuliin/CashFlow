package com.raticuliin.cashflow.bank.infra.out.postgres.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.raticuliin.cashflow.account.infra.out.postgres.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_name")
    private String name;

    @OneToMany(mappedBy = "bankEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<AccountEntity> accounts;


}

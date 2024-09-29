package com.raticuliin.cashflow.category.infra.out.postgres.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.raticuliin.cashflow.transaction.infra.out.postgres.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<TransactionEntity> transactions;

}

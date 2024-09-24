package com.raticuliin.cashflow.category.domain;

import com.raticuliin.cashflow.transaction.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Category {

    private String id;
    private String name;
    private String description;

}

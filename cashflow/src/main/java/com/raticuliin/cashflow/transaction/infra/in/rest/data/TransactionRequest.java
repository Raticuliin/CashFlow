package com.raticuliin.cashflow.transaction.infra.in.rest.data;

import com.raticuliin.cashflow.transaction.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String description;
    @NotNull
    private BigDecimal value;
    @NotNull
    private Boolean isRecurring;
    private LocalDate recurrenceDate;
    private LocalDate transactionDate;
    private Long accountId;
    @NotNull
    private TransactionType transactionType;
    private Long accountFromId;
    private Long accountToId;
    private Long categoryId;

}

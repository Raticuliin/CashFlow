package com.raticuliin.cashflow.transaction.infra.in.rest.mapper;

import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.infra.in.rest.mapper.AccountRestMapper;
import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.category.infra.in.rest.mapper.CategoryRestMapper;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.infra.in.rest.data.TransactionRequest;
import com.raticuliin.cashflow.transaction.infra.in.rest.data.TransactionResponse;

public class TransactionRestMapper {

    public static final Transaction requestToDomain(TransactionRequest request) {

        return Transaction.builder()
                .description(request.getDescription())
                .value(request.getValue())
                .isRecurring(request.getIsRecurring())
                .recurrenceDate(request.getRecurrenceDate())
                .transactionDate(request.getTransactionDate())
                .transactionType(request.getTransactionType())
                .account(
                        request.getAccountId()==null?
                                null:
                                Account.builder()
                                        .id(request.getAccountId())
                                        .build()
                )
                .transactionType(request.getTransactionType())
                .accountFrom(
                        request.getAccountFromId()==null?
                                null:
                                Account.builder()
                                        .id(request.getAccountFromId())
                                        .build()
                )
                .accountTo(
                        request.getAccountToId()==null?
                                null:
                                Account.builder()
                                        .id(request.getAccountToId())
                                        .build()
                )
                .category(
                        request.getCategoryId()==null?
                                null:
                                Category.builder()
                                        .id(request.getCategoryId())
                                        .build()
                )
                .build();

    }

    public static final TransactionResponse domainToResponse(Transaction transaction) {

        return TransactionResponse.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .value(transaction.getValue())
                .isRecurring(transaction.getIsRecurring())
                .recurrenceDate(transaction.getRecurrenceDate())
                .transactionDate(transaction.getTransactionDate())
                .account(
                        transaction.getAccount()==null?
                                null:
                                AccountRestMapper.domainToResponse(transaction.getAccount())
                )
                .transactionType(transaction.getTransactionType())
                .accountFrom(
                        transaction.getAccountFrom()==null?
                                null:
                                AccountRestMapper.domainToResponse(transaction.getAccountFrom())
                )
                .accountTo(
                        transaction.getAccountTo()==null?
                                null:
                                AccountRestMapper.domainToResponse(transaction.getAccountTo())
                )
                .category(
                        CategoryRestMapper.domainToResponse(transaction.getCategory())
                )
                .build();

    }

}

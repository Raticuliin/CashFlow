package com.raticuliin.cashflow.transaction.infra.out.postgres.mapper;

import com.raticuliin.cashflow.account.infra.out.postgres.mapper.AccountEntityMapper;
import com.raticuliin.cashflow.category.infra.out.postgres.mapper.CategoryEntityMapper;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.infra.out.postgres.entity.TransactionEntity;

public class TransactionEntityMapper {

    public final static TransactionEntity domainToEntity(Transaction transaction) {

        return TransactionEntity.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .value(transaction.getValue())
                .isRecurring(transaction.getIsRecurring())
                .recurrenceDate(transaction.getRecurrenceDate())
                .transactionDate(transaction.getTransactionDate())
                .account(
                        transaction.getAccount()==null?
                                null:
                                AccountEntityMapper.domainToEntity(transaction.getAccount())
                )
                .transactionType(transaction.getTransactionType())
                .accountFrom(
                        transaction.getAccountFrom()==null?
                                null:
                                AccountEntityMapper.domainToEntity(transaction.getAccountFrom())
                )
                .accountTo(
                        transaction.getAccountTo()==null?
                                null:
                                AccountEntityMapper.domainToEntity(transaction.getAccountTo())
                )
                .category(
                        CategoryEntityMapper.domainToEntity(transaction.getCategory())
                )
                .build();

    }

    public final static Transaction entityToDomain(TransactionEntity entity) {

        return Transaction.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .value(entity.getValue())
                .isRecurring(entity.getIsRecurring())
                .recurrenceDate(entity.getRecurrenceDate())
                .transactionDate(entity.getTransactionDate())
                .account(
                        entity.getAccount()==null?
                                null:
                                AccountEntityMapper.entityToDomain(entity.getAccount())
                )
                .transactionType(entity.getTransactionType())
                .accountFrom(
                        entity.getAccountFrom()==null?
                                null:
                                AccountEntityMapper.entityToDomain(entity.getAccountFrom())
                )
                .accountTo(
                        entity.getAccountTo()==null?
                                null:
                                AccountEntityMapper.entityToDomain(entity.getAccountTo())
                )
                .category(
                        CategoryEntityMapper.entityToDomain(entity.getCategory())
                )
                .build();

    }

}

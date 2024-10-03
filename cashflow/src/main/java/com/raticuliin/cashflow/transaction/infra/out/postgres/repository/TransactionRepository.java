package com.raticuliin.cashflow.transaction.infra.out.postgres.repository;

import com.raticuliin.cashflow.category.domain.Category;
import com.raticuliin.cashflow.category.infra.out.postgres.mapper.CategoryEntityMapper;
import com.raticuliin.cashflow.transaction.app.out.ITransactionRepository;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import com.raticuliin.cashflow.transaction.infra.out.postgres.mapper.TransactionEntityMapper;
import com.raticuliin.cashflow.transaction.infra.out.postgres.repository.jpa.JpaTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TransactionRepository implements ITransactionRepository {

    private final JpaTransactionRepository jpaTransactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return TransactionEntityMapper.entityToDomain(
                jpaTransactionRepository.save(
                        TransactionEntityMapper.domainToEntity(transaction)));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return jpaTransactionRepository.findAll()
                .stream()
                .map(TransactionEntityMapper::entityToDomain)
                .toList();
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return jpaTransactionRepository.findById(id)
                .map(TransactionEntityMapper::entityToDomain);
    }

    @Override
    public List<Transaction> getTransactionsByFilter(
            TransactionType transactionType,
            Category category,
            Boolean isRecurring,
            LocalDateTime dateFrom,
            LocalDateTime dateTo) {

        return jpaTransactionRepository.findByFilter(
                transactionType,
                        category==null?
                                null:
                                CategoryEntityMapper.domainToEntity(category),
                isRecurring,
                dateFrom,
                dateTo)
                .stream()
                .map(TransactionEntityMapper::entityToDomain)
                .toList();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return TransactionEntityMapper.entityToDomain(
                jpaTransactionRepository.save(
                        TransactionEntityMapper.domainToEntity(transaction)));
    }

    @Override
    public void deleteTransaction(Long id) {
        jpaTransactionRepository.deleteById(id);
    }
}

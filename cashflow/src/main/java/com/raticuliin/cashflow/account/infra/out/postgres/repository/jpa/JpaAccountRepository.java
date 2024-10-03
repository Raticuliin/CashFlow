package com.raticuliin.cashflow.account.infra.out.postgres.repository.jpa;

import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.account.infra.out.postgres.entity.AccountEntity;
import com.raticuliin.cashflow.bank.infra.out.postgres.entity.BankEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    Boolean existsByName(String name);

    @Query(" SELECT a from AccountEntity a " +
                    " WHERE " +
                    " (:accountType IS NULL OR a.accountType = :accountType) AND  " +
                    " (:bank IS NULL OR a.bankEntity = :bank) ")
    List<AccountEntity> findByFilter(
            @Nullable @Param("accountType") AccountType accountType,
            @Nullable @Param("bank") BankEntity bank);

}

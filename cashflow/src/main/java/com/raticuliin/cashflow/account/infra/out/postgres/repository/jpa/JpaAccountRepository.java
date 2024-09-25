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

    /*
    * where
    (:name IS NULL OR LOWER(account.account_name) LIKE LOWER(CONCAT('%', :name, '%'))) and
    (:type IS NULL OR account.account_type = :type) and
    (:bank IS NULL OR account.bank_id = :bank);
    * */

    @Query(" SELECT a from AccountEntity a " +
                    " WHERE " +
                    " (:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%' , :name , '%'))) AND " +
                    " (:accountType IS NULL OR a.accountType = :accountType) AND  " +
                    " (:bank IS NULL OR a.bankEntity = :bank) ")
    List<AccountEntity> findByFilter(
            @Nullable @Param("name") String name,
            @Nullable @Param("accountType") AccountType accountType,
            @Nullable @Param("bank") BankEntity bank);

}

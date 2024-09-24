package com.raticuliin.cashflow.account.infra.out.postgres.repository.jpa;

import com.raticuliin.cashflow.account.infra.out.postgres.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    Boolean existsByName(String name);

    List<AccountEntity> findByNameContainingIgnoreCase(String name);

}

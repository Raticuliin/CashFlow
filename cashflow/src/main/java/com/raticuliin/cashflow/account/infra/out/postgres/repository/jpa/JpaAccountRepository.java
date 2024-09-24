package com.raticuliin.cashflow.account.infra.out.postgres.repository.jpa;

import com.raticuliin.cashflow.account.infra.out.postgres.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
}

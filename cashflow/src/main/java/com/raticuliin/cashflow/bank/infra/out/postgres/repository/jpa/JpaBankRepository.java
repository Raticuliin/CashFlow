package com.raticuliin.cashflow.bank.infra.out.postgres.repository.jpa;

import com.raticuliin.cashflow.bank.infra.out.postgres.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBankRepository extends JpaRepository<BankEntity, Long> {

    Boolean existsByName(String name);

}

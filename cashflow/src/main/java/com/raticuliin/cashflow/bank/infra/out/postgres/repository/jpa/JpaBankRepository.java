package com.raticuliin.cashflow.bank.infra.out.postgres.repository.jpa;

import com.raticuliin.cashflow.bank.infra.out.postgres.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBankRepository extends JpaRepository<BankEntity, Long> {

    Boolean existsByName(String name);
    List<BankEntity> findByNameContainingIgnoreCase(String name);

}

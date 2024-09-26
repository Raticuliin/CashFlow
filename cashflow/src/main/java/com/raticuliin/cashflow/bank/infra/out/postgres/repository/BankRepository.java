package com.raticuliin.cashflow.bank.infra.out.postgres.repository;

import com.raticuliin.cashflow.bank.app.out.IBankRepository;
import com.raticuliin.cashflow.bank.domain.Bank;
import com.raticuliin.cashflow.bank.infra.out.postgres.mapper.BankEntityMapper;
import com.raticuliin.cashflow.bank.infra.out.postgres.repository.jpa.JpaBankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BankRepository implements IBankRepository {

    private final JpaBankRepository jpaBankRepository;

    @Override
    public Bank createBank(Bank bank) {
        return BankEntityMapper.entityToDomain(
                jpaBankRepository.save(BankEntityMapper.domainToEntity(bank))
        );
    }

    @Override
    public boolean existsByName(String name) {
        return jpaBankRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaBankRepository.existsById(id);
    }

    @Override
    public List<Bank> getAllBanks() {
        return jpaBankRepository.findAll()
                .stream()
                .map(BankEntityMapper::entityToDomain)
                .toList();
    }

    @Override
    public Optional<Bank> getBankById(Long id) {
        return jpaBankRepository.findById(id)
                .map(BankEntityMapper::entityToDomain);
    }

    @Override
    public List<Bank> getBanksByFilter(String name) {
        if (name != null)
            return jpaBankRepository.findByNameContainingIgnoreCase(name)
                    .stream()
                    .map(BankEntityMapper::entityToDomain)
                    .toList();

        return getAllBanks();
    }

    @Override
    public Bank updateBank(Bank bank) {
        return BankEntityMapper.entityToDomain(
                jpaBankRepository.save(
                        BankEntityMapper.domainToEntity(bank)));
    }

    @Override
    public void deleteBank(Long id) {
        jpaBankRepository.deleteById(id);
    }

}

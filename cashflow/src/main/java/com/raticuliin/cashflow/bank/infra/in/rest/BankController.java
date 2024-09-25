package com.raticuliin.cashflow.bank.infra.in.rest;

import com.raticuliin.cashflow.bank.app.in.usecase.*;
import com.raticuliin.cashflow.bank.infra.in.rest.data.BankRequest;
import com.raticuliin.cashflow.bank.infra.in.rest.data.BankResponse;
import com.raticuliin.cashflow.bank.infra.in.rest.mapper.BankMapper;
import com.raticuliin.cashflow.utils.data.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@AllArgsConstructor
public class BankController {

    private final CreateBankUseCase createBankUseCase;

    private final GetAllBanksUseCase getAllBanksUseCase;

    private final GetBankByIdUseCase getBankByIdUseCase;

    private final GetBankByNameContaining getBankByNameContaining;

    private final UpdateBankUseCase updateBankUseCase;

    private final DeleteBankUseCase deleteBankUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BankRequest createBankRequest) {

        BankResponse bankResponse;

        try {
            bankResponse = BankMapper.domainToBankResponse(createBankUseCase.createBank(BankMapper.bankRequestToDomain(createBankRequest)));
        } catch (Exception e) {

            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(bankResponse);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBanks() {

        List<BankResponse> bankResponseList;

        try {
            bankResponseList = getAllBanksUseCase.getAllBanks()
                    .stream()
                    .map(BankMapper::domainToBankResponse)
                    .toList();
        } catch (Exception e) {

            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

        return ResponseEntity.ok(bankResponseList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBankById(@PathVariable("id") Long id) {

        BankResponse bankResponse;

        try {
            bankResponse = BankMapper.domainToBankResponse(getBankByIdUseCase.getBankById(id));
        } catch (Exception e) {

            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }

        return ResponseEntity.ok(bankResponse);

    }

    @GetMapping("")
    public ResponseEntity<?> getBanksByNameContaining(@RequestParam("name") String name) {

        List<BankResponse> bankResponseList;

        try {
            bankResponseList = getBankByNameContaining.getBankByNameContaining(name)
                    .stream()
                    .map(BankMapper::domainToBankResponse)
                    .toList();
        } catch (Exception e) {

            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

        return ResponseEntity.ok(bankResponseList);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBank(@PathVariable("id") Long id, @RequestBody BankRequest updateBankRequest) {

        BankResponse bankResponse;

        try {
            bankResponse = BankMapper.domainToBankResponse(updateBankUseCase.updateBank(id, BankMapper.bankRequestToDomain(updateBankRequest)));
        } catch (Exception e) {

            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(bankResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable("id") Long id) {

        BankResponse bankResponse;

        try {
            bankResponse = BankMapper.domainToBankResponse(deleteBankUseCase.deleteBank(id));
        } catch (Exception e) {

            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }

        return ResponseEntity.ok(bankResponse);

    }

}

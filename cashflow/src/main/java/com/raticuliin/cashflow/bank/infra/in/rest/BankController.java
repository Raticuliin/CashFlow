package com.raticuliin.cashflow.bank.infra.in.rest;

import com.raticuliin.cashflow.bank.app.in.BankService;
import com.raticuliin.cashflow.bank.app.in.usecase.CreateBankUseCase;
import com.raticuliin.cashflow.bank.infra.in.rest.data.BankResponse;
import com.raticuliin.cashflow.bank.infra.in.rest.data.CreateBankRequest;
import com.raticuliin.cashflow.bank.infra.in.rest.mapper.BankMapper;
import com.raticuliin.cashflow.bank.utils.data.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    CreateBankUseCase createBankUseCase;
    @Autowired
    private BankService bankService;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody CreateBankRequest createBankRequest) {

        BankResponse bankResponse;

        try {
            bankResponse = BankMapper.domainToBankResponse(createBankUseCase.create(BankMapper.createBankRequestToDomain(createBankRequest)));
        } catch (Exception e) {

            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(bankResponse);

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllBanks() {

        List<BankResponse> bankResponseList;

        try {
            bankResponseList = bankService.getAllBanks()
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
    public ResponseEntity<?> getBankById(@PathVariable("id") long id) {

        BankResponse bankResponse;

        try {
            bankResponse = BankMapper.domainToBankResponse(bankService.getBankById(id));
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
    public ResponseEntity<?> getBankByNameContaining(@RequestParam("name") String name) {

        List<BankResponse> bankResponseList;

        try {
            bankResponseList = bankService.getBankByNameContaining(name)
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

}

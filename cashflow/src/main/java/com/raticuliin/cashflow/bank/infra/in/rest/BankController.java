package com.raticuliin.cashflow.bank.infra.in.rest;

import com.raticuliin.cashflow.bank.app.in.usecase.CreateBankUseCase;
import com.raticuliin.cashflow.bank.infra.in.rest.data.BankResponse;
import com.raticuliin.cashflow.bank.infra.in.rest.data.CreateBankRequest;
import com.raticuliin.cashflow.bank.infra.in.rest.mapper.BankMapper;
import com.raticuliin.cashflow.bank.utils.data.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    CreateBankUseCase createBankUseCase;

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

}

package com.raticuliin.cashflow.account.infra.in.rest;

import com.raticuliin.cashflow.account.app.in.usecase.CreateAccountUseCase;
import com.raticuliin.cashflow.account.infra.in.rest.data.AccountRequest;
import com.raticuliin.cashflow.account.infra.in.rest.data.AccountResponse;
import com.raticuliin.cashflow.account.infra.in.rest.mapper.AccountMapper;
import com.raticuliin.cashflow.utils.data.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private CreateAccountUseCase createAccountUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {

        AccountResponse accountResponse;

        try {
            accountResponse = AccountMapper.domainToResponse(
                    createAccountUseCase.createAccount(
                            AccountMapper.requestToDomain(accountRequest)));
        } catch (Exception e) {
            ErrorResponse response = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(accountResponse);

    }


}

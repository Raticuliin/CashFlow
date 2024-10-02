package com.raticuliin.cashflow.account.infra.in.rest;

import com.raticuliin.cashflow.account.app.in.AccountService;
import com.raticuliin.cashflow.account.app.in.usecase.*;
import com.raticuliin.cashflow.account.domain.Account;
import com.raticuliin.cashflow.account.domain.AccountType;
import com.raticuliin.cashflow.account.infra.in.rest.data.AccountRequest;
import com.raticuliin.cashflow.account.infra.in.rest.data.AccountResponse;
import com.raticuliin.cashflow.account.infra.in.rest.data.AccountResumeListResponse;
import com.raticuliin.cashflow.account.infra.in.rest.mapper.AccountRestMapper;
import com.raticuliin.cashflow.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    private final GetAllAccountsUseCase getAllAccountsUseCase;

    private final GetAccountByIdUseCase getAccountByIdUseCase;

    private final GetAccountsByFilterUseCase getAccountsByFilterUseCase;

    private final UpdateAccountUseCase updateAccountUseCase;

    private final DeleteAccountUseCase deleteAccountUseCase;

    private final GetTotalBalanceUseCase getTotalBalanceUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {

        AccountResponse accountResponse;

        try {
            accountResponse = AccountRestMapper.domainToResponse(
                    createAccountUseCase.createAccount(
                            AccountRestMapper.requestToDomain(accountRequest)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(accountResponse);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {

        AccountResumeListResponse accountResumeListResponse;

        try {

            List<AccountResponse> accountResponseList;
            List<Account> response = getAllAccountsUseCase.getAllAccounts();

            accountResponseList = response
                    .stream()
                    .map(AccountRestMapper::domainToResponse)
                    .toList();

            accountResumeListResponse = AccountResumeListResponse.builder()
                    .totalBalance(getTotalBalanceUseCase.getTotalBalance(response))
                    .accountList(accountResponseList)
                    .build();

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Utils.getErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
        }

        return ResponseEntity.ok(accountResumeListResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {

        AccountResponse accountResponseList;

        try {
            accountResponseList = AccountRestMapper.domainToResponse(
                    getAccountByIdUseCase.getAccountById(id)
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(accountResponseList);

    }

    @GetMapping("/filter")
    public ResponseEntity<?> getAccountsByFilter(
            @RequestParam(
                    required = false, name = "name") String name,
            @RequestParam(
                    required = false, name = "type") AccountType type,
            @RequestParam(
                    required = false, name = "bank") Long bankId) {

        List<AccountResponse> accountResponseList;

        try {
            accountResponseList = getAccountsByFilterUseCase.getAccountsByFilter(name, type, bankId)
                    .stream()
                    .map(AccountRestMapper::domainToResponse)
                    .toList();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Utils.getErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
        }

        return ResponseEntity.ok(accountResponseList);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {

        AccountResponse accountResponse;

        try {

            accountResponse = AccountRestMapper.domainToResponse(
                    updateAccountUseCase.updateAccount(
                            id,
                            AccountRestMapper.requestToDomain(accountRequest)));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(accountResponse);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {

        AccountResponse accountResponse;

        try {

            accountResponse = AccountRestMapper.domainToResponse(
                    deleteAccountUseCase.deleteAccount(id));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(accountResponse);

    }

}

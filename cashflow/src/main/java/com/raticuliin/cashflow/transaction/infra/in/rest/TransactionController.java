package com.raticuliin.cashflow.transaction.infra.in.rest;

import com.raticuliin.cashflow.account.app.in.usecase.GetAllAccountsUseCase;
import com.raticuliin.cashflow.account.app.in.usecase.GetTotalBalanceUseCase;
import com.raticuliin.cashflow.account.domain.AccountResume;
import com.raticuliin.cashflow.transaction.app.in.usecase.*;
import com.raticuliin.cashflow.transaction.domain.Transaction;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import com.raticuliin.cashflow.transaction.infra.in.rest.data.TransactionHistoryResponse;
import com.raticuliin.cashflow.transaction.infra.in.rest.data.TransactionRequest;
import com.raticuliin.cashflow.transaction.infra.in.rest.data.TransactionResponse;
import com.raticuliin.cashflow.transaction.infra.in.rest.mapper.TransactionRestMapper;
import com.raticuliin.cashflow.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;
    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final GetTransactionsByFilterUseCase getTransactionsByFilterUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final GetTransactionHistory getTransactionHistoryUseCase;

    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final GetTotalBalanceUseCase getTotalBalanceUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest request) {

        TransactionResponse response;

        try {

            response = TransactionRestMapper.domainToResponse(
                    createTransactionUseCase.createTransaction(
                            TransactionRestMapper.requestToDomain(request)));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTransactions() {

        List<TransactionResponse> response;

        try {
            response = getAllTransactionsUseCase.getAllTransactions()
                    .stream()
                    .map(TransactionRestMapper::domainToResponse)
                    .toList();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable("id") Long id) {

        TransactionResponse response;

        try {
            response = TransactionRestMapper.domainToResponse(
                    getTransactionByIdUseCase.getTransactionById(id)
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(response);

    }

    @GetMapping("/filter")
    public ResponseEntity<?> getTransactionsByFilter(
            @RequestParam(
                    required = false, name = "transactionType") TransactionType transactionType,
            @RequestParam(
                    required = false, name = "category") Long categoryId,
            @RequestParam(
                    required = false, name = "isRecurring") Boolean isRecurring,
            @RequestParam(
                    required = false, name = "dateFrom") LocalDate dateFrom,
            @RequestParam(
                    required = false, name = "dateTo") LocalDate dateTo) {

        List<TransactionResponse> response;

        try {
            response = getTransactionsByFilterUseCase.getTransactionByFilter(
                        transactionType,
                        categoryId,
                        isRecurring,
                        dateFrom,
                        dateTo)
                    .stream()
                    .map(TransactionRestMapper::domainToResponse)
                    .toList();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Utils.getErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
        }

        return ResponseEntity.ok(response);

    }

    @GetMapping("/history")
    public ResponseEntity<?> getTransactionHistory(
            @RequestParam(
                    name = "days") Integer daysFrom) {

        try {

            List<Transaction> transactionList = getTransactionsByFilterUseCase.getTransactionByFilter(
                    null,
                    null,
                    null,
                    LocalDate.now().minusDays(daysFrom),
                    LocalDate.now().plusDays(1)
            );

            List<AccountResume> accountList = getAllAccountsUseCase.getAllAccounts().stream().map(account -> AccountResume.builder()
                    .id(account.getId())
                    .name(account.getName())
                    .balance(account.getBalance())
                    .build()).toList();

            TransactionHistoryResponse transactionHistoryResponse = TransactionHistoryResponse.builder()
                    .totalHistory(getTransactionHistoryUseCase.getTransactionHistory(transactionList, getTotalBalanceUseCase.getTotalBalance(accountList)))
                    .accountHistoryList(
                            accountList.stream().map(
                                    account -> getTransactionHistoryUseCase.getTransactionHistoryByAccount(transactionList, account)
                            ).toList()
                    )
                    .build();

            return ResponseEntity.ok(transactionHistoryResponse);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Utils.getErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest request) {

        TransactionResponse response;

        try {

            response = TransactionRestMapper.domainToResponse(
                    updateTransactionUseCase.updateTransaction(
                            id,
                            TransactionRestMapper.requestToDomain(request)));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {

        TransactionResponse response;

        try {

            response = TransactionRestMapper.domainToResponse(
                    deleteTransactionUseCase.deleteTransaction(id));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(response);

    }

}

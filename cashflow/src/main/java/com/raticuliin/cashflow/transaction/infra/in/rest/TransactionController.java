package com.raticuliin.cashflow.transaction.infra.in.rest;

import com.raticuliin.cashflow.transaction.app.in.usecase.*;
import com.raticuliin.cashflow.transaction.domain.TransactionType;
import com.raticuliin.cashflow.transaction.infra.in.rest.data.TransactionRequest;
import com.raticuliin.cashflow.transaction.infra.in.rest.data.TransactionResponse;
import com.raticuliin.cashflow.transaction.infra.in.rest.mapper.TransactionRestMapper;
import com.raticuliin.cashflow.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final DeleteTransactionuseCase deleteTransactionuseCase;

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
    public ResponseEntity<?> getAccountsByFilter(
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
                    deleteTransactionuseCase.deleteTransaction(id));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Utils.getErrorResponse(e, HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok(response);

    }

}

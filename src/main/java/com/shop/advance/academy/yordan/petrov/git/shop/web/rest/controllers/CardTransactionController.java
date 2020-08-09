package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class controller for the transaction.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/transactions")
@Slf4j
public class CardTransactionController {

    private final TransactionService transactionService;

    /**
     * Constructor
     */
    @Autowired
    public CardTransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionServiceViewModel> updateTransaction(@RequestBody TransactionServiceModel transactionServiceModel) {
        TransactionServiceViewModel transactionServiceViewModel = transactionService.updateTransaction(transactionServiceModel);
        log.info("Transaction updated: {}", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(transactionServiceViewModel);
    }

    @PatchMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionServiceViewModel> partialUpdateTransaction(@RequestBody TransactionServiceModel transactionServiceModel) {
        TransactionServiceViewModel transactionServiceViewModel = transactionService.updateTransaction(transactionServiceModel);
        log.info("Transaction updated: {} , ", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(transactionServiceViewModel);
    }


    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionServiceViewModel> getTransaction(@PathVariable("id") final Long id) {
        TransactionServiceViewModel transactionServiceViewModel = transactionService.getTransactionById(id);
        log.info("Transaction Found: {} ", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(transactionServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()  and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<TransactionServiceViewModel>> getTransactions() {
        List<TransactionServiceViewModel> transactionServiceViewModel = transactionService.getAllTransactions();
        log.info("Transactions Found: {} ", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(transactionServiceViewModel);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<TransactionServiceViewModel> deleteTransaction(@PathVariable("id") Long id) {
        TransactionServiceViewModel transactionServiceViewModel = transactionService.deleteTransactionById(id);
        log.info("Transaction deleted: {} ", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(transactionServiceViewModel);
    }


}

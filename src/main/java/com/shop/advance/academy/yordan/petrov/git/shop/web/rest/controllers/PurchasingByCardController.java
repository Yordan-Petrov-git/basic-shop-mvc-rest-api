package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.PurchasingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchases")
@Slf4j
public class PurchasingByCardController {

    private final PurchasingService purchasingService;

    @Autowired
    public PurchasingByCardController(PurchasingService purchasingService) {
        this.purchasingService = purchasingService;
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionServiceViewModel> creatPurchase(@RequestBody TransactionServiceModel transactionServiceModel) {
        TransactionServiceViewModel transactionServiceViewModel = purchasingService.payByCard(transactionServiceModel);
        log.info("Purchased : {}", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionServiceViewModel> refundPurchase(@RequestBody TransactionServiceModel transactionServiceModel) {
        TransactionServiceViewModel transactionServiceViewModel = purchasingService.refundCardPurchase(transactionServiceModel);
        log.info("Refunded : {}", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionServiceViewModel);
    }

}

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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

/**
 * Class controller for the purchase.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/purchases")
@Slf4j
public class PurchasingByCardController {

    private final PurchasingService purchasingService;

    /**
     * Constructor
     */
    @Autowired
    public PurchasingByCardController(PurchasingService purchasingService) {
        this.purchasingService = purchasingService;
    }

    /**
     * Method for
     *
     * @param transactionServiceModel
     * @return
     */
    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionServiceViewModel> creatPurchase(@RequestBody TransactionServiceModel transactionServiceModel) {
        TransactionServiceViewModel transactionServiceViewModel = purchasingService.payByCard(transactionServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(PurchasingByCardController.class, "creatPurchase", TransactionServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(transactionServiceViewModel.getId()).toUri();
        log.info("Purchased : {} {}", transactionServiceViewModel, location);
        return ResponseEntity.created(location).body(transactionServiceViewModel);
    }

    /**
     * Method for
     *
     * @param transactionServiceModel
     * @return
     */
    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionServiceViewModel> refundPurchase(@RequestBody TransactionServiceModel transactionServiceModel) {
        TransactionServiceViewModel transactionServiceViewModel = purchasingService.refundCardPurchase(transactionServiceModel);
        log.info("Refunded : {}", transactionServiceViewModel);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionServiceViewModel);
    }

}

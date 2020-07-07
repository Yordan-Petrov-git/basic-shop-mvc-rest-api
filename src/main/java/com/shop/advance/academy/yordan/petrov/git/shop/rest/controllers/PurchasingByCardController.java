package com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.PurchasingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TransactionServiceViewModel> creatPurchase(@RequestBody TransactionServiceModel transactionServiceModel) {

        TransactionServiceViewModel transactionServiceViewModel = purchasingService.payByCard(transactionServiceModel);

        log.info("Purchased : {}", transactionServiceViewModel);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionServiceViewModel);

    }

    @GetMapping()
    public ResponseEntity<TransactionServiceViewModel>refundPurchase(@RequestBody TransactionServiceModel transactionServiceModel) {

        TransactionServiceViewModel transactionServiceViewModel = purchasingService.refundCardPurchase(transactionServiceModel);

        log.info("Refunded : {}", transactionServiceViewModel);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionServiceViewModel);

    }

    //TODO ADD REFUND PURCHASE REVERSE TRANSACTION CHANGE STATUS TO REFUNDED CHANGE STATUS OF ORDER TO REFUNDED

//    @PutMapping("/{id}")
//    public ResponseEntity<AddressServiceViewModel> updateAddress(@PathVariable("id") Long id, @RequestBody AddressServiceModel addressServiceModel) {
//
//
//        AddressServiceViewModel addressServiceViewModel = addressService.updateAddress(addressServiceModel);
//
//        log.info("Address  updated : {}", addressServiceViewModel);
//
//        return ResponseEntity.status(HttpStatus.OK).body(addressServiceViewModel);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AddressServiceViewModel> getAddress(@PathVariable("id") final Long id) {
//
//        AddressServiceViewModel addressServiceViewModel = addressService.getAddressById(id);
//
//        log.info("Address  found : {}", addressServiceViewModel);
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(addressServiceViewModel);
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<AddressServiceViewModel>> getAddress() {
//
//        List<AddressServiceViewModel> addressServiceViewModel = addressService.getAllAddresses();
//
//        log.info("Addresses  found : {}", addressServiceViewModel);
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(addressServiceViewModel);
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<AddressServiceViewModel> deleteAddress(@PathVariable("id") Long id) {
//
//
//        AddressServiceViewModel addressServiceViewModel = addressService.deleteAddressById(id);
//
//        log.info("Address deleted : {}", addressServiceViewModel);
//
//        return ResponseEntity.status(HttpStatus.OK).body(addressServiceViewModel);
//    }


}

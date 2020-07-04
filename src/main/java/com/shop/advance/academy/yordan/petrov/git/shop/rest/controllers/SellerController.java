package com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/seller")
@Slf4j
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<SellerServiceViewModel> createSeller(@RequestBody SellerServiceModel sellerServiceModel) {

        SellerServiceViewModel sellerServiceViewModel = sellerService.createSeller(sellerServiceModel);

        log.info("Seller  created : {}", sellerServiceViewModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(sellerServiceViewModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerServiceViewModel> updateSeller(@PathVariable("id") Long id, @RequestBody SellerServiceModel sellerServiceModel) {

        SellerServiceViewModel sellerServiceViewModel = sellerService.updateSeller(sellerServiceModel);

        log.info("Seller  found : {}", sellerServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(sellerServiceViewModel);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SellerServiceViewModel> getSeller(@PathVariable("id") final Long id) {


        SellerServiceViewModel sellerServiceViewModel = sellerService.getSellerById(id);

        log.info("Seller  found : {}", sellerServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(sellerServiceViewModel);
    }

    @GetMapping()
    public ResponseEntity<List<SellerServiceViewModel>> getSellers() {

        List<SellerServiceViewModel> sellerServiceViewModels = sellerService.getAllSellers();

        log.info("Seller Found: {} ", sellerServiceViewModels);

        return ResponseEntity.status(HttpStatus.FOUND).body(sellerServiceViewModels);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SellerServiceViewModel> deleteSeller(@PathVariable("id") Long id) {

        SellerServiceViewModel sellerServiceViewModel = sellerService.deleteSellerById(id);

        log.info("Seller  deleted : {}", sellerServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(sellerServiceViewModel);
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/register")
    public ResponseEntity<SellerServiceModel> createSeller(@RequestBody SellerServiceModel sellerServiceModel) {
        sellerService.createSeller(sellerServiceModel);
        return new ResponseEntity<>(sellerServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateSeller(@PathVariable("id") Long id,@RequestBody SellerServiceModel sellerServiceModel) {
        sellerService.updateSeller(sellerServiceModel);
    }


    @GetMapping("/{id}")
    public SellerServiceViewModel getSeller(@PathVariable("id")final Long id) {
        return sellerService.getSellerById(id);
    }

    @GetMapping()
    public List<SellerServiceViewModel> getSellers() {
        return sellerService.getAllSellers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSeller(@PathVariable("id") Long id) {
        sellerService.deleteSellerById(id);
    }

}

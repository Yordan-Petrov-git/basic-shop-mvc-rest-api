package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/currency")
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<CurrencyServiceModel> createCurrency(@RequestBody CurrencyServiceModel currencyServiceModel) {
        currencyService.createCurrency(currencyServiceModel);
        return new ResponseEntity<>(currencyServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<> updateCurrency(@PathVariable("id") Long id,@RequestBody CurrencyServiceModel currencyServiceModel) {
        currencyService.updateCurrency(currencyServiceModel);
    }


    @GetMapping("/{id}")
    public CurrencyServiceViewModel getCurrency(@PathVariable("id")final Long id) {
        return currencyService.getCurrencyById(id);
    }

    @GetMapping()
    public  ResponseEntity<List<CurrencyServiceViewModel>> getCurrencies() {

        List<CurrencyServiceViewModel> currencyServiceViewModels =  currencyService.getAllCurrencies();

        log.info("Currency Found: {} ", currencyServiceViewModels);

        return ResponseEntity.status(HttpStatus.FOUND).body(currencyServiceViewModels);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<> deleteCurrency(@PathVariable("id") Long id) {
        currencyService.deleteCurrencyById(id);
    }

}

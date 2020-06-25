package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/currency")
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
    public void updateCurrency(@PathVariable("id") Long id,@RequestBody CurrencyServiceModel currencyServiceModel) {
        currencyService.updateCurrency(currencyServiceModel);
    }


    @GetMapping("/{id}")
    public CurrencyServiceViewModel getCurrency(@PathVariable("id")final Long id) {
        return currencyService.getCurrencyById(id);
    }

    @GetMapping()
    public List<CurrencyServiceViewModel> getCurrencys() {
        return currencyService.getAllCurrencies();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCurrency(@PathVariable("id") Long id) {
        currencyService.deleteCurrencyById(id);
    }

}

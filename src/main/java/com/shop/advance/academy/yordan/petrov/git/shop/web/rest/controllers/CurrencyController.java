package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceViewModel;
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


    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CurrencyServiceViewModel> createCurrency(@RequestBody CurrencyServiceModel currencyServiceModel) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.createCurrency(currencyServiceModel);
        log.info("Currency  created : {}", currencyServiceViewModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CurrencyServiceViewModel> updateCurrency(@PathVariable("id") Long id, @RequestBody CurrencyServiceModel currencyServiceModel) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.updateCurrency(currencyServiceModel);
        log.info("Currency  updated : {}", currencyServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(currencyServiceViewModel);
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<CurrencyServiceViewModel> getCurrency(@PathVariable("id") final Long id) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.getCurrencyById(id);
        log.info("Currency  found : {}", currencyServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(currencyServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<CurrencyServiceViewModel>> getCurrencies() {
        List<CurrencyServiceViewModel> currencyServiceViewModels = currencyService.getAllCurrencies();
        log.info("Currency Found: {} ", currencyServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(currencyServiceViewModels);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CurrencyServiceViewModel> deleteCurrency(@PathVariable("id") Long id) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.deleteCurrencyById(id);
        log.info("Currency  deleted : {}", currencyServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(currencyServiceViewModel);
    }

}

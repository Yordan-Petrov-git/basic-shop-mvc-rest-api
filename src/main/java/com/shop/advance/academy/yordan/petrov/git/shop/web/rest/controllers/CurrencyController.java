package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Class controller for the currency.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/currency")
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    /**
     * Constructor
     */
    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    /**
     * Method for
     *
     * @param currencyServiceModel
     * @return
     */
    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CurrencyServiceViewModel> createCurrency(@RequestBody CurrencyServiceModel currencyServiceModel) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.createCurrency(currencyServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(CurrencyController.class, "createCurrency", CurrencyServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(currencyServiceViewModel.getId()).toUri();
        log.info("Currency  created : {} {}", currencyServiceViewModel, location);
        return ResponseEntity.created(location).body(currencyServiceViewModel);
    }

    /**
     * Method for
     *
     * @param currencyServiceModel
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CurrencyServiceViewModel> updateCurrency(@RequestBody CurrencyServiceModel currencyServiceModel) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.updateCurrency(currencyServiceModel);
        log.info("Currency  updated : {}", currencyServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(currencyServiceViewModel);
    }


    /**
     * Method for
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<CurrencyServiceViewModel> getCurrency(@PathVariable("id") final Long id) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.getCurrencyById(id);
        log.info("Currency  found : {}", currencyServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(currencyServiceViewModel);
    }

    /**
     * Method for
     *
     * @return
     */
    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<CurrencyServiceViewModel>> getCurrencies() {
        List<CurrencyServiceViewModel> currencyServiceViewModels = currencyService.getAllCurrencies();
        log.info("Currency Found: {} ", currencyServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(currencyServiceViewModels);
    }

    /**
     * Method for
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CurrencyServiceViewModel> deleteCurrency(@PathVariable("id") Long id) {
        CurrencyServiceViewModel currencyServiceViewModel = currencyService.deleteCurrencyById(id);
        log.info("Currency  deleted : {}", currencyServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(currencyServiceViewModel);
    }

}

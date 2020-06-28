package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CountryServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CountryServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/country")
@Slf4j
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<CountryServiceModel> createCountry(@RequestBody CountryServiceModel countryServiceModel) {
        countryService.createCountry(countryServiceModel);
        return new ResponseEntity<>(countryServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateCountry(@PathVariable("id") Long id,@RequestBody CountryServiceModel countryServiceModel) {
        countryService.updateCountry(countryServiceModel);
    }


    @GetMapping("/{id}")
    public CountryServiceViewModel getCountry(@PathVariable("id")final Long id) {
        return countryService.getCountryById(id);
    }

    @GetMapping()
    public List<CountryServiceViewModel> getCountrys() {
        return countryService.getAllCountries();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCountry(@PathVariable("id") Long id) {
        countryService.deleteCountryById(id);
    }

}

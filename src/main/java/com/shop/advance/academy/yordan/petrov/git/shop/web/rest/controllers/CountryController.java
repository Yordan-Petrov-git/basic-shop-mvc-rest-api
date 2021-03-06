package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CountryService;
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
 * Class controller for the country.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/country")
@Slf4j
public class CountryController {

    private final CountryService countryService;

    /**
     * Constructor
     */
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    /**
     * Method for
     *
     * @param countryServiceModel
     * @return
     */
    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CountryServiceViewModel> createCountry(@RequestBody CountryServiceModel countryServiceModel) {
        CountryServiceViewModel countryServiceViewModel = countryService.createCountry(countryServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(CountryController.class, "createCountry", CountryServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(countryServiceViewModel.getId()).toUri();
        log.info("Country  created : {} {}", countryServiceViewModel, location);
        return ResponseEntity.created(location).body(countryServiceViewModel);
    }

    /**
     * Method for
     *
     * @param countryServiceModel
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CountryServiceViewModel> updateCountry(@RequestBody CountryServiceModel countryServiceModel) {
        CountryServiceViewModel countryServiceViewModel = countryService.updateCountry(countryServiceModel);
        log.info("Country  Updated : {}", countryServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(countryServiceViewModel);
    }


    /**
     * Method for
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<CountryServiceViewModel> getCountry(@PathVariable("id") final Long id) {
        CountryServiceViewModel countryServiceViewModel = countryService.getCountryById(id);
        log.info("Country  found : {}", countryServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(countryServiceViewModel);
    }

    /**
     * Method for
     *
     * @return
     */
    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<CountryServiceViewModel>> getCountries() {
        List<CountryServiceViewModel> countryServiceViewModels = countryService.getAllCountries();
        log.info("Country Found: {} ", countryServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(countryServiceViewModels);
    }

    /**
     * Method for
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CountryServiceViewModel> deleteCountry(@PathVariable("id") Long id) {
        CountryServiceViewModel countryServiceViewModel = countryService.deleteCountryById(id);
        log.info("Country  deleted : {}", countryServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(countryServiceViewModel);
    }

}

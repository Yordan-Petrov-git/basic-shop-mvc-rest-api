package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/city")
@Slf4j
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CityServiceViewModel> createCity(@RequestBody CityServiceModel cityServiceModel) {
        CityServiceViewModel cityServiceViewModel = cityService.createCity(cityServiceModel);
        log.info("City  created : {}", cityServiceViewModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CityServiceViewModel> updateCity(@PathVariable("id") Long id, @RequestBody CityServiceModel cityServiceModel) {
        CityServiceViewModel cityServiceViewModel = cityService.updateCity(id);
        log.info("City  UPDATED : {}", cityServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cityServiceViewModel);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CityServiceViewModel> updateSpecificAttributesCity(@PathVariable("id") Long id, @RequestBody CityServiceModel cityServiceModel) {
        CityServiceViewModel cityServiceViewModel = cityService.updateCity(id);
        log.info("City  UPDATED : {}", cityServiceViewModel);
        return new ResponseEntity<>(cityServiceViewModel, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<CityServiceViewModel> getCity(@PathVariable("id") final Long id) {
        CityServiceViewModel cityServiceViewModel = cityService.getCityById(id);
        log.info("City  FOUND : {}", cityServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(cityServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<CityServiceViewModel>> getCites() {
        List<CityServiceViewModel> cityServiceViewModels = cityService.getAllCities();
        log.info("Cities Found: {} ", cityServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(cityServiceViewModels);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CityServiceViewModel> deleteCity(@PathVariable("id") Long id) {
        CityServiceViewModel cityServiceViewModel = cityService.deleteCityById(id);
        log.info("City deleted: {} ", cityServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cityServiceViewModel);
    }

}

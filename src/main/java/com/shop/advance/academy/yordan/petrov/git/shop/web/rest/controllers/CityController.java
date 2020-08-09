package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CityServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CityServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
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
 * Class controller for the city.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/city")
@Slf4j
public class CityController {

    private final CityService cityService;

    /**
     * Constructor
     */
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CityServiceViewModel> createCity(@RequestBody CityServiceModel cityServiceModel) {
        CityServiceViewModel cityServiceViewModel = cityService.createCity(cityServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(CityController.class, "createCity", CityServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(cityServiceViewModel.getId()).toUri();
        log.info("City  created : {} {}", cityServiceViewModel, location);
        return ResponseEntity.created(location).body(cityServiceViewModel);
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

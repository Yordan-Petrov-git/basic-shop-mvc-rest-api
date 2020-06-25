package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<CityServiceModel> createCity(@RequestBody CityServiceModel cityServiceModel) {
        cityService.createCity(cityServiceModel);
        return new ResponseEntity<>(cityServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateCity(@PathVariable("id") Long id,@RequestBody CityServiceModel cityServiceModel) {
        cityService.updateCity(cityServiceModel);
    }


    @GetMapping("/{id}")
    public CityServiceViewModel getCity(@PathVariable("id")final Long id) {
        return cityService.getCityById(id);
    }

    @GetMapping()
    public List<CityServiceViewModel> getCitys() {
        return cityService.getAllCities();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCityById(id);
    }

}

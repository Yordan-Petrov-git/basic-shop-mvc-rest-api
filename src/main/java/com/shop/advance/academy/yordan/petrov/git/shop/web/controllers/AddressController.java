package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
@Slf4j
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<AddressServiceModel> createAddress(@RequestBody AddressServiceModel addressServiceModel) {
        addressService.createAddress(addressServiceModel);
        return new ResponseEntity<>(addressServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<> updateAddress(@PathVariable("id") Long id,@RequestBody AddressServiceModel addressServiceModel) {
        addressService.updateAddress(addressServiceModel);
    }


    @GetMapping("/{id}")
    public AddressServiceViewModel getAddress(@PathVariable("id")final Long id) {
        return addressService.getAddressById(id);
    }

    @GetMapping()
    public List<AddressServiceViewModel> getAddress() {
        return addressService.getAllAddresses();
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<> deleteAddress(@PathVariable("id") Long id) {
        addressService.getAddressById(id);
    }


}

package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

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

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AddressServiceViewModel> createAddress(@RequestBody AddressServiceModel addressServiceModel) {
        AddressServiceViewModel addressServiceViewModel = addressService.createAddress(addressServiceModel);
        log.info("Address  created : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AddressServiceViewModel> updateAddress(@PathVariable("id") Long id, @RequestBody AddressServiceModel addressServiceModel) {
        AddressServiceViewModel addressServiceViewModel = addressService.updateAddress(addressServiceModel);
        log.info("Address  updated : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(addressServiceViewModel);
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AddressServiceViewModel> getAddress(@PathVariable("id") final Long id) {
        AddressServiceViewModel addressServiceViewModel = addressService.getAddressById(id);
        log.info("Address  found : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(addressServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<AddressServiceViewModel>> getAddress() {
        List<AddressServiceViewModel> addressServiceViewModel = addressService.getAllAddresses();
        log.info("Addresses  found : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(addressServiceViewModel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AddressServiceViewModel> deleteAddress(@PathVariable("id") Long id) {
        AddressServiceViewModel addressServiceViewModel = addressService.deleteAddressById(id);
        log.info("Address deleted : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(addressServiceViewModel);
    }


}

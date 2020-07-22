package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.AddressServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
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
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<AddressServiceViewModel> createAddress(@RequestBody AddressServiceModel addressServiceModel) {
        AddressServiceViewModel addressServiceViewModel = addressService.createAddress(addressServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(AddressController.class, "createAddress", AddressServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(addressServiceViewModel.getId()).toUri();
        log.info("Address  created : {} {}", addressServiceViewModel, location);
        return ResponseEntity.created(location).body(addressServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<AddressServiceViewModel> updateAddress(@RequestBody AddressServiceModel addressServiceModel) {
        AddressServiceViewModel addressServiceViewModel = addressService.updateAddress(addressServiceModel);
        log.info("Address  updated : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(addressServiceViewModel);
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<AddressServiceViewModel> getAddress(@PathVariable("id") final Long id) {
        AddressServiceViewModel addressServiceViewModel = addressService.getAddressById(id);
        log.info("Address  found : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(addressServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<List<AddressServiceViewModel>> getAddress() {
        List<AddressServiceViewModel> addressServiceViewModel = addressService.getAllAddresses();
        log.info("Addresses  found : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(addressServiceViewModel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<AddressServiceViewModel> deleteAddress(@PathVariable("id") Long id) {
        AddressServiceViewModel addressServiceViewModel = addressService.deleteAddressById(id);
        log.info("Address deleted : {}", addressServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(addressServiceViewModel);
    }

}

package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
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
 * Class controller for the seller.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/seller")
@Slf4j
public class SellerController {

    private final SellerService sellerService;

    /**
     * Constructor
     */
    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<SellerServiceViewModel> createSeller(@RequestBody SellerServiceModel sellerServiceModel) {
        SellerServiceViewModel sellerServiceViewModel = sellerService.createSeller(sellerServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(SellerController.class, "createSeller", SellerServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(sellerServiceViewModel.getId()).toUri();
        log.info("Seller  created : {} {}", sellerServiceViewModel, location);
        return ResponseEntity.created(location).body(sellerServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<SellerServiceViewModel> updateSeller(@RequestBody SellerServiceModel sellerServiceModel) {
        SellerServiceViewModel sellerServiceViewModel = sellerService.updateSeller(sellerServiceModel);
        log.info("Seller  found : {}", sellerServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(sellerServiceViewModel);
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<SellerServiceViewModel> getSeller(@PathVariable("id") final Long id) {
        SellerServiceViewModel sellerServiceViewModel = sellerService.getSellerById(id);
        log.info("Seller  found : {}", sellerServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(sellerServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<SellerServiceViewModel>> getSellers() {
        List<SellerServiceViewModel> sellerServiceViewModels = sellerService.getAllSellers();
        log.info("Seller Found: {} ", sellerServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(sellerServiceViewModels);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<SellerServiceViewModel> deleteSeller(@PathVariable("id") Long id) {
        SellerServiceViewModel sellerServiceViewModel = sellerService.deleteSellerById(id);
        log.info("Seller  deleted : {}", sellerServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(sellerServiceViewModel);
    }
}

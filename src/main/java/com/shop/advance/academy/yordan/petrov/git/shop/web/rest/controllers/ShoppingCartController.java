package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
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
 * Class controller for the shopping cart.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/cart")
@Slf4j
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    /**
     * Constructor
     */
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Method for
     *
     * @param shoppingCartServiceModel
     * @return
     */
    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ShoppingCartServiceViewModel> createShoppingCart(@RequestBody ShoppingCartServiceModel shoppingCartServiceModel) {
        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.createShoppingCart(shoppingCartServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(ShoppingCartController.class, "createShoppingCart", ShoppingCartServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(shoppingCartServiceViewModel.getId()).toUri();
        log.info("Shopping Cart created: {} {}", shoppingCartServiceViewModel, location);
        return ResponseEntity.created(location).body(shoppingCartServiceViewModel);
    }

    /**
     * Method for
     *
     * @param shoppingCartServiceModel
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ShoppingCartServiceViewModel> updateShoppingCart(@RequestBody ShoppingCartServiceModel shoppingCartServiceModel) {
        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.updateShoppingCart(shoppingCartServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "updateShoppingCart", UserServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(shoppingCartServiceViewModel.getId()).toUri();
        log.info("Shopping cart updated: {} {}", shoppingCartServiceViewModel, location);
        return ResponseEntity.status(HttpStatus.FOUND).body(shoppingCartServiceViewModel);
    }


    /**
     * Method for
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ShoppingCartServiceViewModel> getShoppingCart(@PathVariable("id") final Long id) {
        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.getShoppingCartById(id);
        log.info("Shopping cart found: {}", shoppingCartServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(shoppingCartServiceViewModel);
    }

    /**
     * Method for
     *
     * @return
     */
    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<ShoppingCartServiceViewModel>> getShoppingCarts() {
        List<ShoppingCartServiceViewModel> shoppingCartServiceViewModels = shoppingCartService.getAllShoppingCarts();
        log.info("Shopping Carts Found: {} ", shoppingCartServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(shoppingCartServiceViewModels);

    }

    /**
     * Method for
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ShoppingCartServiceViewModel> deleteShoppingCart(@PathVariable("id") Long id) {
        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.deleteShoppingCartById(id);
        log.info("Shopping Cart deleted: {}", shoppingCartServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartServiceViewModel);
    }


}

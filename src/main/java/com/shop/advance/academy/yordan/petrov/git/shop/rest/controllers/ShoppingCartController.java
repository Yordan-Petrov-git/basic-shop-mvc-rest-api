package com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("api/cart")
@Slf4j
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/register")
    public ResponseEntity<ShoppingCartServiceViewModel> createShoppingCart(@RequestBody ShoppingCartServiceModel shoppingCartServiceModel) {

        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.createShoppingCart(shoppingCartServiceModel);

        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "createShoppingCart", UserServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(shoppingCartServiceViewModel.getId()).toUri();

        log.info("Shopping Cart created: {}", location);

        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartServiceViewModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartServiceViewModel> updateShoppingCart(@PathVariable("id") Long id, @RequestBody ShoppingCartServiceModel shoppingCartServiceModel) {

        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.updateShoppingCart(shoppingCartServiceModel);

        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "updateShoppingCart", UserServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(shoppingCartServiceViewModel.getId()).toUri();

        log.info("Shopping cart updated: {} {}", shoppingCartServiceViewModel, location);

        return ResponseEntity.status(HttpStatus.FOUND).body(shoppingCartServiceViewModel);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartServiceViewModel> getShoppingCart(@PathVariable("id") final Long id) {

        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.getShoppingCartById(id);

        log.info("Shopping cart found: {}", shoppingCartServiceViewModel);

        return ResponseEntity.status(HttpStatus.FOUND).body(shoppingCartServiceViewModel);
    }

    @GetMapping()
    public ResponseEntity<List<ShoppingCartServiceViewModel>> getShoppingCarts() {

        List<ShoppingCartServiceViewModel> shoppingCartServiceViewModels = shoppingCartService.getAllShoppingCarts();

        log.info("Shopping Carts Found: {} ", shoppingCartServiceViewModels);

        return ResponseEntity.status(HttpStatus.FOUND).body(shoppingCartServiceViewModels);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ShoppingCartServiceViewModel> deleteShoppingCart(@PathVariable("id") Long id) {

        ShoppingCartServiceViewModel shoppingCartServiceViewModel = shoppingCartService.deleteShoppingCartById(id);

        log.info("Shopping Cart deleted: {}", shoppingCartServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartServiceViewModel);
    }


}

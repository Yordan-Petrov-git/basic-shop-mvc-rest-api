package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<ShoppingCartServiceModel> createShoppingCart(@RequestBody ShoppingCartServiceModel shoppingCartServiceModel) {
        shoppingCartService.createShoppingCart(shoppingCartServiceModel);
        return new ResponseEntity<>(shoppingCartServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateShoppingCart(@PathVariable("id") Long id, @RequestBody ShoppingCartServiceModel shoppingCartServiceModel) {
        shoppingCartService.updateShoppingCart(shoppingCartServiceModel);
    }


    @GetMapping("/{id}")
    public ShoppingCartServiceViewModel getShoppingCart(@PathVariable("id") final Long id) {
        return shoppingCartService.getShoppingCartById(id);
    }

    @GetMapping()
    public List<ShoppingCartServiceViewModel> getShoppingCarts() {
        return shoppingCartService.getAllShoppingCarts();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShoppingCart(@PathVariable("id") Long id) {
        shoppingCartService.deleteShoppingCartById(id);
    }


}

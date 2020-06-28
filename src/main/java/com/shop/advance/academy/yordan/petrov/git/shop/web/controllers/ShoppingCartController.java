package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
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

        ShoppingCartServiceViewModel shoppingCartServiceViewModel  = shoppingCartService.createShoppingCart(shoppingCartServiceModel);


        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "createUser", UserServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(shoppingCartServiceViewModel.getId()).toUri();

        log.info("Shopping Cart created: {}", location);

        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartServiceViewModel);
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

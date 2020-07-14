package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {

    ShoppingCartServiceViewModel createShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel);

    ShoppingCartServiceViewModel updateShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel);

    ShoppingCartServiceViewModel getShoppingCartById(long id);

    List<ShoppingCartServiceViewModel> getAllShoppingCarts();

    ShoppingCartServiceViewModel deleteShoppingCartById(long id);
}

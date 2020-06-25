package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ShoppingCartService {

    ShoppingCartServiceModel createShoppingCart(ShoppingCartServiceModel ShoppingCart);

    void updateShoppingCart(ShoppingCartServiceModel ShoppingCart);

    ShoppingCartServiceViewModel getShoppingCartById(long id);

    List<ShoppingCartServiceViewModel> getAllShoppingCarts();

    void deleteShoppingCartById(long id);


}

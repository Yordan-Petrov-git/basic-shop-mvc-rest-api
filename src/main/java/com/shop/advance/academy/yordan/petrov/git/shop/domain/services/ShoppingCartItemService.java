package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartItemServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartItemServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ShoppingCartItemService {

    ShoppingCartItemServiceViewModel createShoppingCartItem(ShoppingCartItemServiceModel shoppingCartItemServiceModel);

    ShoppingCartItemServiceViewModel updateShoppingCartItem(ShoppingCartItemServiceModel shoppingCartItemServiceModel);

    ShoppingCartItemServiceViewModel getShoppingCartItemById(long id);

    List<ShoppingCartItemServiceViewModel> getAllShoppingCartItems();

    ShoppingCartItemServiceViewModel deleteShoppingCartItemById(long id);


}

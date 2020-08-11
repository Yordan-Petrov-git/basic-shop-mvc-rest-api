package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface ShoppingCartService {

    /**
     * @param shoppingCartServiceModel
     * @return
     */
    ShoppingCartServiceViewModel createShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel);

    /**
     * @param shoppingCartServiceModel
     * @return
     */
    ShoppingCartServiceViewModel updateShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel);

    /**
     * @param id
     * @return
     */
    ShoppingCartServiceViewModel getShoppingCartById(long id);

    /**
     * @return
     */
    List<ShoppingCartServiceViewModel> getAllShoppingCarts();

    /**
     * @param id
     * @return
     */
    ShoppingCartServiceViewModel deleteShoppingCartById(long id);
}

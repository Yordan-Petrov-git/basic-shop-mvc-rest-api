package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ModelMapper modelMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShoppingCartServiceModel createShoppingCart(ShoppingCartServiceModel ShoppingCart) {
        return null;
    }

    @Override
    public void updateShoppingCart(ShoppingCartServiceModel ShoppingCart) {

    }

    @Override
    public ShoppingCartServiceViewModel getShoppingCartById(long id) {
        return null;
    }

    @Override
    public List<ShoppingCartServiceViewModel> getAllShoppingCarts() {
        return null;
    }

    @Override
    public void deleteShoppingCartById(long id) {

    }
}

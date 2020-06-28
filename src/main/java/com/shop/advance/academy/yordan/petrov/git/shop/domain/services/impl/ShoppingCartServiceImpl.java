package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    public ShoppingCartServiceViewModel createShoppingCart(ShoppingCartServiceModel ShoppingCart) {

        ShoppingCart shoppingCart = this.modelMapper.map(ShoppingCart, ShoppingCart.class);

        return this.modelMapper.map(this.shoppingCartRepository.saveAndFlush(shoppingCart), ShoppingCartServiceViewModel.class);

    }

    @Override
    @Transactional
    public ShoppingCartServiceViewModel updateShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel) {

        ShoppingCart shoppingCart = this.modelMapper.map(shoppingCartServiceModel, ShoppingCart.class);

        this.shoppingCartRepository.findById(shoppingCartServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Shopping with id '%d' not found .", shoppingCartServiceModel.getId())));

       return this.modelMapper.map(this.shoppingCartRepository.saveAndFlush(shoppingCart), ShoppingCartServiceViewModel.class);

    }

    @Override
    public ShoppingCartServiceViewModel getShoppingCartById(long id) {

        return this.modelMapper
                .map(this.shoppingCartRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Shopping cart with ID %s not found.", id))), ShoppingCartServiceViewModel.class);

    }

    @Override
    public List<ShoppingCartServiceViewModel> getAllShoppingCarts() {

        this.shoppingCartRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Shopping carts were found"));

        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();

        return modelMapper.map(shoppingCarts, new TypeToken<List<ShoppingCartServiceViewModel>>() {
        }.getType());
    }

    @Override
    public ShoppingCartServiceViewModel deleteShoppingCartById(long id) {

        this.shoppingCartRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Shopping Cart with id '%d' not found .", id)));

        ShoppingCartServiceViewModel deletedShoppingCart = this.getShoppingCartById(id);


        this.shoppingCartRepository.deleteById(id);

        return this.modelMapper.map(deletedShoppingCart, ShoppingCartServiceViewModel.class);

    }
}

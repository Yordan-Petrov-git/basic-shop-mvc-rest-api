package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        ShoppingCart shoppingCart = this.modelMapper.map(ShoppingCart, ShoppingCart.class);
        return this.modelMapper.map( this.shoppingCartRepository.saveAndFlush(shoppingCart), ShoppingCartServiceModel.class);
    }

    @Override
    public void updateShoppingCart(ShoppingCartServiceModel ShoppingCart) {
        ShoppingCart shoppingCart = this.modelMapper.map(ShoppingCart, ShoppingCart.class);
         this.modelMapper.map( this.shoppingCartRepository.saveAndFlush(shoppingCart), ShoppingCartServiceModel.class);
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
                .orElseThrow((InvalidEntityException::new));

        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();

        return modelMapper.map(shoppingCarts, new TypeToken<List<ShoppingCartServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteShoppingCartById(long id) {

        this.shoppingCartRepository.findById(id)
                .orElseThrow((InvalidEntityException::new));

        this.shoppingCartRepository.deleteById(id);
    }
}

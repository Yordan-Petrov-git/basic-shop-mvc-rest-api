package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartItemRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartItemService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingCartItemServiceImpl(ShoppingCartItemRepository shoppingCartItemRepository, ItemRepository itemRepository, ItemService itemService, ModelMapper modelMapper) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShoppingCartItemServiceViewModel createShoppingCartItem(ShoppingCartItemServiceModel shoppingCartItemServiceModel) {

        ShoppingCartItem shoppingCartItem = this.modelMapper.map(shoppingCartItemServiceModel, ShoppingCartItem.class);

//        this.itemRepository.findById(shoppingCartItem.getId()).ifPresent(c -> {
//            throw new InvalidEntityException(String.format("Item '%s' already exists.", shoppingCartItemServiceModel.getItem().getTitle()));
//        });


        //Create shopping cart item only if the item is already in the database
//        ItemServiceViewModel itemServiceViewModel = this.itemService.getItemById(shoppingCartItemServiceModel.getItem().getId());
//
//        itemRepository.findById(shoppingCartItemServiceModel.getItem().getId())
//                .ifPresent(c -> {
//                    shoppingCartItem.setItem(this.modelMapper.map(itemServiceViewModel, Item.class));
//                });

        return this.modelMapper.map(this.shoppingCartItemRepository.saveAndFlush(shoppingCartItem), ShoppingCartItemServiceViewModel.class);

    }

    @Override
    public ShoppingCartItemServiceViewModel updateShoppingCartItem(ShoppingCartItemServiceModel shoppingCartItemServiceModel) {
        ShoppingCartItem shoppingCartItem = this.modelMapper.map(shoppingCartItemServiceModel, ShoppingCartItem.class);
        return this.modelMapper.map(this.shoppingCartItemRepository.saveAndFlush(shoppingCartItem), ShoppingCartItemServiceViewModel.class);
    }

    @Override
    public ShoppingCartItemServiceViewModel getShoppingCartItemById(long id) {

        return this.modelMapper
                .map(this.shoppingCartItemRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Item  with ID %s not found.", id))), ShoppingCartItemServiceViewModel.class);

    }

    @Override
    public List<ShoppingCartItemServiceViewModel> getAllShoppingCartItems() {
        this.shoppingCartItemRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Items were found"));

        List<ShoppingCartItem> item = this.shoppingCartItemRepository.findAll();

        return this.modelMapper.map(item, new TypeToken<List<ShoppingCartItemServiceViewModel>>() {
        }.getType());
    }

    @Override
    public ShoppingCartItemServiceViewModel deleteShoppingCartItemById(long id) {

        ShoppingCartItemServiceViewModel deleteShoppingCartItem = this.getShoppingCartItemById(id);

        this.shoppingCartItemRepository.deleteById(id);

        return this.modelMapper.map(deleteShoppingCartItem, ShoppingCartItemServiceViewModel.class);
    }
}

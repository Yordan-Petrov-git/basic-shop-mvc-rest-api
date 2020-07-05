package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartItemRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ItemCountPair;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemCountPairServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemCountPairServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemCountPairService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemCountPairCountPairServiceImpl implements ItemCountPairService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemCountPairCountPairServiceImpl(ShoppingCartItemRepository shoppingCartItemRepository, ModelMapper modelMapper) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemCountPairServiceViewModel createShoppingCartItem(ItemCountPairServiceModel itemCountPairServiceModel) {

        ItemCountPair itemCountPair = this.modelMapper.map(itemCountPairServiceModel, ItemCountPair.class);

        return this.modelMapper.map(this.shoppingCartItemRepository.saveAndFlush(itemCountPair), ItemCountPairServiceViewModel.class);
    }


    @Override
    public ItemCountPairServiceViewModel updateShoppingCartItem(ItemCountPairServiceModel itemCountPairServiceModel) {
        ItemCountPair itemCountPair = this.modelMapper.map(itemCountPairServiceModel, ItemCountPair.class);
        return this.modelMapper.map(this.shoppingCartItemRepository.saveAndFlush(itemCountPair), ItemCountPairServiceViewModel.class);
    }

    @Override
    public ItemCountPairServiceViewModel getShoppingCartItemById(long id) {

        return this.modelMapper
                .map(this.shoppingCartItemRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Item  with ID %s not found.", id))), ItemCountPairServiceViewModel.class);

    }

    @Override
    public List<ItemCountPairServiceViewModel> getAllShoppingCartItems() {
        this.shoppingCartItemRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Items were found"));

        List<ItemCountPair> item = this.shoppingCartItemRepository.findAll();

        return this.modelMapper.map(item, new TypeToken<List<ItemCountPairServiceViewModel>>() {
        }.getType());
    }

    @Override
    public ItemCountPairServiceViewModel deleteShoppingCartItemById(long id) {

        ItemCountPairServiceViewModel deleteShoppingCartItem = this.getShoppingCartItemById(id);

        this.shoppingCartItemRepository.deleteById(id);

        return this.modelMapper.map(deleteShoppingCartItem, ItemCountPairServiceViewModel.class);
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemServiceModel createItem(ItemServiceModel itemServiceModel) {
        Item item = this.modelMapper.map(itemServiceModel, Item.class);
        return this.modelMapper.map( this.itemRepository.saveAndFlush(item), ItemServiceModel.class);
    }

    @Override
    public void updateItem(ItemServiceModel Item) {

    }

    @Override
    public ItemServiceViewModel getItemById(long id) {
        return this.modelMapper
                .map(this.itemRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Item  with ID %s not found.", id))), ItemServiceViewModel.class);
    }

    @Override
    public List<ItemServiceViewModel> getAllItems() {
        List<Item> items = itemRepository.findAll();

        return modelMapper.map(items, new TypeToken<List<ItemServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }
}

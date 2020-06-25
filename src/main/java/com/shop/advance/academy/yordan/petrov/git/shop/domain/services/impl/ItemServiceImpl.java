package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ItemServiceModel createItem(ItemServiceModel Item) {
        return null;
    }

    @Override
    public void updateItem(ItemServiceModel Item) {

    }

    @Override
    public ItemServiceViewModel getItemById(long id) {
        return null;
    }

    @Override
    public List<ItemServiceViewModel> getAllItems() {
        return null;
    }

    @Override
    public void deleteItemById(long id) {

    }
}

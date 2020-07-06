package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemCountPairRepository;
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
public class ItemCountPairServiceImpl implements ItemCountPairService {

    private final ItemCountPairRepository itemCountPairRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemCountPairServiceImpl(ItemCountPairRepository itemCountPairRepository, ModelMapper modelMapper) {
        this.itemCountPairRepository = itemCountPairRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemCountPairServiceViewModel createShoppingCartItem(ItemCountPairServiceModel itemCountPairServiceModel) {

        ItemCountPair itemCountPair = this.modelMapper.map(itemCountPairServiceModel, ItemCountPair.class);

        return this.modelMapper.map(this.itemCountPairRepository.saveAndFlush(itemCountPair), ItemCountPairServiceViewModel.class);
    }


    @Override
    public ItemCountPairServiceViewModel updateShoppingCartItem(ItemCountPairServiceModel itemCountPairServiceModel) {
        ItemCountPair itemCountPair = this.modelMapper.map(itemCountPairServiceModel, ItemCountPair.class);
        return this.modelMapper.map(this.itemCountPairRepository.saveAndFlush(itemCountPair), ItemCountPairServiceViewModel.class);
    }

    @Override
    public ItemCountPairServiceViewModel getShoppingCartItemById(long id) {

        return this.modelMapper
                .map(this.itemCountPairRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Item  with ID %s not found.", id))), ItemCountPairServiceViewModel.class);

    }

    @Override
    public List<ItemCountPairServiceViewModel> getAllShoppingCartItems() {
        this.itemCountPairRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Items were found"));

        List<ItemCountPair> item = this.itemCountPairRepository.findAll();

        return this.modelMapper.map(item, new TypeToken<List<ItemCountPairServiceViewModel>>() {
        }.getType());
    }

    @Override
    public ItemCountPairServiceViewModel deleteShoppingCartItemById(long id) {

        ItemCountPairServiceViewModel deleteShoppingCartItem = this.getShoppingCartItemById(id);

        this.itemCountPairRepository.deleteById(id);

        return this.modelMapper.map(deleteShoppingCartItem, ItemCountPairServiceViewModel.class);
    }
}

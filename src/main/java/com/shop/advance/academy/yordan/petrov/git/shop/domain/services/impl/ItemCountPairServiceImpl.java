package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemCountPairDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ItemCountPair;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemCountPairServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemCountPairServiceViewModel;
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

    private final ItemCountPairDao itemCountPairDao;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemCountPairServiceImpl(ItemCountPairDao itemCountPairDao, ModelMapper modelMapper) {
        this.itemCountPairDao = itemCountPairDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemCountPairServiceViewModel createItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel) {
        ItemCountPair itemCountPair = mapItemCountPairServiceModelToItemCountPair(itemCountPairServiceModel);
        this.itemCountPairDao.saveAndFlush(itemCountPair);
        return mapItemCountPairToItemCountPairServiceViewModel(itemCountPair);
    }

    @Override
    public ItemCountPairServiceViewModel updateItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel) {
        ItemCountPair itemCountPair = mapItemCountPairServiceModelToItemCountPair(itemCountPairServiceModel);
        this.itemCountPairDao.saveAndFlush(itemCountPair);
        return mapItemCountPairToItemCountPairServiceViewModel(itemCountPair);
    }

    @Override
    public ItemCountPairServiceViewModel getItemCountPairById(long id) {
        return mapItemCountPairToItemCountPairServiceViewModel(findShoppingCartById(id));
    }

    @Override
    public List<ItemCountPairServiceViewModel> getAllItemCountPairs() {
        validateIfFoundAny();
        List<ItemCountPair> item = getItemCountPairsList();
        return mapItemCountPairListToItemCountPairServiceViewModelList(item);
    }

    @Override
    public ItemCountPairServiceViewModel deleteItemCountPairById(long id) {
        ItemCountPairServiceViewModel deleteShoppingCartItem = this.getItemCountPairById(id);
        this.itemCountPairDao.deleteById(id);
        return deleteShoppingCartItem;
    }

    private ItemCountPairServiceViewModel mapItemCountPairToItemCountPairServiceViewModel(ItemCountPair itemCountPair) {
        return this.modelMapper.map(itemCountPair, ItemCountPairServiceViewModel.class);
    }

    private ItemCountPair mapItemCountPairServiceModelToItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel) {
        return this.modelMapper.map(itemCountPairServiceModel, ItemCountPair.class);
    }

    private ItemCountPair findShoppingCartById(long id) {
        return this.itemCountPairDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Item  with ID %s not found.", id)));
    }

    private List<ItemCountPairServiceViewModel> mapItemCountPairListToItemCountPairServiceViewModelList(List<ItemCountPair> item) {
        return this.modelMapper.map(item, new TypeToken<List<ItemCountPairServiceViewModel>>() {
        }.getType());
    }

    private List<ItemCountPair> getItemCountPairsList() {
        return this.itemCountPairDao.findAll();
    }

    private void validateIfFoundAny() {
        this.itemCountPairDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Items were found"));
    }
}

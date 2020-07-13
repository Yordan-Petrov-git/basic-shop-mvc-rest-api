package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.ItemCountPairRepository;
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
    public ItemCountPairServiceViewModel createItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel) {
        ItemCountPair itemCountPair = mapItemCountPairServiceModelToItemCountPair(itemCountPairServiceModel);
        this.itemCountPairRepository.saveAndFlush(itemCountPair);
        return mapItemCountPairToItemCountPairServiceViewModel(itemCountPair);
    }

    @Override
    public ItemCountPairServiceViewModel updateItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel) {
        ItemCountPair itemCountPair = mapItemCountPairServiceModelToItemCountPair(itemCountPairServiceModel);
        this.itemCountPairRepository.saveAndFlush(itemCountPair);
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
        this.itemCountPairRepository.deleteById(id);
        return deleteShoppingCartItem;
    }

    private ItemCountPairServiceViewModel mapItemCountPairToItemCountPairServiceViewModel(ItemCountPair itemCountPair) {
        return this.modelMapper.map(itemCountPair, ItemCountPairServiceViewModel.class);
    }

    private ItemCountPair mapItemCountPairServiceModelToItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel) {
        return this.modelMapper.map(itemCountPairServiceModel, ItemCountPair.class);
    }

    private ItemCountPair findShoppingCartById(long id) {
        return this.itemCountPairRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Item  with ID %s not found.", id)));
    }

    private List<ItemCountPairServiceViewModel> mapItemCountPairListToItemCountPairServiceViewModelList(List<ItemCountPair> item) {
        return this.modelMapper.map(item, new TypeToken<List<ItemCountPairServiceViewModel>>() {
        }.getType());
    }

    private List<ItemCountPair> getItemCountPairsList() {
        return this.itemCountPairRepository.findAll();
    }

    private void validateIfFoundAny() {
        this.itemCountPairRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Items were found"));
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final ModelMapper modelMapper;

    /**
     * Constructor
     */
    @Autowired
    public ItemServiceImpl(ItemDao itemDao, ModelMapper modelMapper) {
        this.itemDao = itemDao;
        this.modelMapper = modelMapper;
    }

    /**
     * @param itemServiceModel
     * @return
     */
    @Override
    public ItemServiceViewModel createItem(ItemServiceModel itemServiceModel) {
        Item item = mapItemServiceModelToItem(itemServiceModel);
        this.itemDao.findByTitleAndDescription(itemServiceModel.getTitle(), itemServiceModel.getDescription()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Item with title '%s' and description '%s' already exists.", itemServiceModel.getTitle(), itemServiceModel.getDescription()));
        });
        this.itemDao.saveAndFlush(item);
        return mapItemToItemServiceViewModel(item);
    }

    /**
     * @param itemServiceModel
     * @return
     */
    @Override
    @Transactional
    public ItemServiceViewModel updateItem(ItemServiceModel itemServiceModel) {
        Item item = mapItemServiceModelToItem(itemServiceModel);
        getItemById(itemServiceModel.getId());
        this.itemDao.saveAndFlush(item);
        return mapItemToItemServiceViewModel(item);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public ItemServiceViewModel getItemById(long id) {
        return this.modelMapper
                .map(this.itemDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Item  with ID %s not found.", id))), ItemServiceViewModel.class);
    }

    /**
     * @param title
     * @return
     */
    @Override
    public ItemServiceViewModel getItemByTitle(String title) {
        return this.modelMapper
                .map(this.itemDao.findByTitle(title).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Item  with title %s not found.", title))), ItemServiceViewModel.class);
    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<ItemServiceViewModel> getItemByTitleLike(String title) {
        return modelMapper.map(this.itemDao.findByTitleLike(title), new TypeToken<List<ItemServiceViewModel>>() {
        }.getType());
    }

    /**
     * @return
     */
    @Override
    public List<ItemServiceViewModel> getAllItems() {
        this.itemDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Items were found"));
        return modelMapper.map(itemDao.findAll(), new TypeToken<List<ItemServiceViewModel>>() {
        }.getType());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ItemServiceViewModel deleteItemById(long id) {
        ItemServiceViewModel itemServiceViewModel = this.getItemById(id);
        this.itemDao.deleteById(id);
        return itemServiceViewModel;
    }


    /**
     * @param item
     * @return
     */
    private ItemServiceViewModel mapItemToItemServiceViewModel(Item item) {
        return this.modelMapper.map(item, ItemServiceViewModel.class);
    }

    /**
     * @param itemServiceModel
     * @return
     */
    private Item mapItemServiceModelToItem(ItemServiceModel itemServiceModel) {
        return this.modelMapper.map(itemServiceModel, Item.class);
    }
}

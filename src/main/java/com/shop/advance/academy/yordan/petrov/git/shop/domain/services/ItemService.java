package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    ItemServiceViewModel createItem(ItemServiceModel Item);

    ItemServiceViewModel updateItem(ItemServiceModel Item);

    ItemServiceViewModel getItemById(long id);

    List<ItemServiceViewModel> getAllItems();

    ItemServiceViewModel deleteItemById(long id);


}

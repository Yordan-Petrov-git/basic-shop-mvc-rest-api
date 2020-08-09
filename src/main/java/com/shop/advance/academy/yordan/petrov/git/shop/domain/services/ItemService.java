package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface ItemService extends ItemSearchService {

    ItemServiceViewModel createItem(ItemServiceModel Item);

    ItemServiceViewModel updateItem(ItemServiceModel Item);

    ItemServiceViewModel getItemById(long id);

    List<ItemServiceViewModel> getAllItems();

    ItemServiceViewModel deleteItemById(long id);
}

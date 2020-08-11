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

    /**
     * @param Item
     * @return
     */
    ItemServiceViewModel createItem(ItemServiceModel Item);

    /**
     * @param Item
     * @return
     */
    ItemServiceViewModel updateItem(ItemServiceModel Item);

    /**
     * @param id
     * @return
     */
    ItemServiceViewModel getItemById(long id);

    /**
     * @return
     */
    List<ItemServiceViewModel> getAllItems();

    /**
     * @param id
     * @return
     */
    ItemServiceViewModel deleteItemById(long id);
}

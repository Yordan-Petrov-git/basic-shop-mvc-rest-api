package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemCountPairServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemCountPairServiceViewModel;
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
public interface ItemCountPairService {

    ItemCountPairServiceViewModel createItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel);

    ItemCountPairServiceViewModel updateItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel);

    ItemCountPairServiceViewModel getItemCountPairById(long id);

    List<ItemCountPairServiceViewModel> getAllItemCountPairs();

    ItemCountPairServiceViewModel deleteItemCountPairById(long id);
}

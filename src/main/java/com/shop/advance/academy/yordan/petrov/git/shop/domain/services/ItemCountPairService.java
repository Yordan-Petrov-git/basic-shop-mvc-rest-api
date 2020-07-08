package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemCountPairServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemCountPairServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemCountPairService {

    ItemCountPairServiceViewModel createItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel);

    ItemCountPairServiceViewModel updateItemCountPair(ItemCountPairServiceModel itemCountPairServiceModel);

    ItemCountPairServiceViewModel getItemCountPairById(long id);

    List<ItemCountPairServiceViewModel> getAllItemCountPairs();

    ItemCountPairServiceViewModel deleteItemCountPairById(long id);


}

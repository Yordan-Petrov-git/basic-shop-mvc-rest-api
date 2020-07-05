package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemCountPairServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemCountPairServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemCountPirService {

    ItemCountPairServiceViewModel createShoppingCartItem(ItemCountPairServiceModel itemCountPairServiceModel);

    ItemCountPairServiceViewModel updateShoppingCartItem(ItemCountPairServiceModel itemCountPairServiceModel);

    ItemCountPairServiceViewModel getShoppingCartItemById(long id);

    List<ItemCountPairServiceViewModel> getAllShoppingCartItems();

    ItemCountPairServiceViewModel deleteShoppingCartItemById(long id);


}

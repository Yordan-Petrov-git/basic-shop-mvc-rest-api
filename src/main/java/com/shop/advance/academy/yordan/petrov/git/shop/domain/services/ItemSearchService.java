package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemSearchService {

    ItemServiceViewModel getItemByTitle(String title);

    List<ItemServiceViewModel> getItemByTitleLike(String title);
}

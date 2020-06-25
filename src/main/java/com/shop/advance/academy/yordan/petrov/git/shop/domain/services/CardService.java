package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface  CardService {

    CardServiceModel createCard(CardServiceModel cardServiceModel);

    void updateCard(CardServiceModel cardServiceModel);

    CardServiceViewModel getCardById(long id);

    List<CardServiceViewModel> getAllCards();

    void deleteCardById(long id);


}

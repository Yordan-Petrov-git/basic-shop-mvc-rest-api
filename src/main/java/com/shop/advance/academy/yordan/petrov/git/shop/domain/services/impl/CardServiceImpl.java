package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CardRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl   implements CardService {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, ModelMapper modelMapper) {
        this.cardRepository = cardRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CardServiceModel createCard(CardServiceModel cardServiceModel) {
        return null;
    }

    @Override
    public void updateCard(CardServiceModel cardServiceModel) {

    }

    @Override
    public CardServiceViewModel getCardById(long id) {
        return null;
    }

    @Override
    public List<CardServiceViewModel> getAllCards() {
        return null;
    }

    @Override
    public void deleteCardById(long id) {

    }
}

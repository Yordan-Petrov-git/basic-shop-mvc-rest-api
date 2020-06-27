package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CardRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, ModelMapper modelMapper) {
        this.cardRepository = cardRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CardServiceModel createCard(CardServiceModel cardServiceModel) {
        Card card = this.modelMapper.map(cardServiceModel, Card.class);
        return this.modelMapper.map(this.cardRepository.saveAndFlush(card), CardServiceModel.class);
    }

    @Override
    public void updateCard(CardServiceModel cardServiceModel) {

    }

    @Override
    public CardServiceViewModel getCardById(long id) {
        return this.modelMapper
                .map(this.cardRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Card  with ID %s not found.", id))), CardServiceViewModel.class);
    }

    @Override
    public List<CardServiceViewModel> getAllCards() {

        this.cardRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Cards were found"));

        List<Card> cards = cardRepository.findAll();

        return modelMapper.map(cards, new TypeToken<List<CardServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteCardById(long id) {

        this.cardRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Card  with id '%d' not found .", id)));

        this.cardRepository.deleteById(id);
    }
}

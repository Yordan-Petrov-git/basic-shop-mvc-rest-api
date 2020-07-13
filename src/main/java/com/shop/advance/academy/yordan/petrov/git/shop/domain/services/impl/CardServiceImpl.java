package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.CardRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.CurrencyRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.UserRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Currency;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder, CurrencyService currencyService,
                           CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CardServiceViewModel createCard(CardServiceModel cardServiceModel) {
        Card card = mapCardServiceModelToCard(cardServiceModel);
        findCardByNumber(cardServiceModel);
        setCurrencyByCurrencyName(cardServiceModel, card);
        card.setDateIssued(LocalDateTime.now());
        card.setUsers(addUsersToCards(cardServiceModel));
        card.setPinCode(this.bCryptPasswordEncoder.encode(cardServiceModel.getPinCode()));
        this.cardRepository.saveAndFlush(card);
        return mapCardToCardServiceViewModel(card);
    }

    @Transactional
    public List<User> addUsersToCards(CardServiceModel cardServiceModel) {
        List<User> userList = new ArrayList<>();
        cardServiceModel.getUsers().forEach(u -> {
            User user = userRepository.findById(u.getId())
                    .orElseThrow(InvalidEntityException::new);
            userList.add(user);
        });
        return userList;
    }

    @Override
    @Transactional
    public CardServiceViewModel updateCard(CardServiceModel cardServiceModel) {
        Card card = mapCardServiceModelToCard(cardServiceModel);
        getCardById(cardServiceModel.getId());
        card.setUsers(addUsersToCards(cardServiceModel));
        this.cardRepository.saveAndFlush(card);
        return mapCardToCardServiceViewModel(card);
    }

    @Override
    public CardServiceViewModel getCardById(long id) {
        return mapCardToCardServiceViewModel(getCardByIdFromRepository(id));
    }

    @Override
    public List<CardServiceViewModel> getAllCards() {
        validateIfCardsExists();
        List<Card> cards = cardRepository.findAll();
        return mapCardListToCardServiceViewModelList(cards);
    }

    @Override
    public CardServiceViewModel deleteCardById(long id) {
        CardServiceViewModel cardServiceViewModel = this.getCardById(id);
        this.cardRepository.deleteById(id);
        return cardServiceViewModel;

    }

    private void validateIfCardsExists() {
        this.cardRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Cards were found"));
    }

    private List<CardServiceViewModel> mapCardListToCardServiceViewModelList(List<Card> cards) {
        return modelMapper.map(cards, new TypeToken<List<CardServiceViewModel>>() {
        }.getType());
    }

    private Card getCardByIdFromRepository(long id) {

        return this.cardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Card  with ID %s not found.", id)));
    }

    private CardServiceViewModel mapCardToCardServiceViewModel(Card card) {
        return this.modelMapper.map(card, CardServiceViewModel.class);
    }

    private Card mapCardServiceModelToCard(CardServiceModel cardServiceModel) {
        return this.modelMapper.map(cardServiceModel, Card.class);
    }

    private void findCardByNumber(CardServiceModel cardServiceModel) {
        this.cardRepository.findByNumber(cardServiceModel.getNumber()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Card with number '%s' already exists.", cardServiceModel.getNumber()));
        });
    }

    private void setCurrencyByCurrencyName(CardServiceModel cardServiceModel, Card card) {
        currencyRepository.findByName(cardServiceModel.getCurrency().getName())
                .ifPresent(c -> {
                    card.setCurrency(this.modelMapper.map(getCurrencyServiceViewModel(cardServiceModel), Currency.class));
                });
    }

    private CurrencyServiceViewModel getCurrencyServiceViewModel(CardServiceModel cardServiceModel) {
        return this.currencyService.getCurrencyByName(cardServiceModel.getCurrency().getName());
    }

}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CardDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Currency;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CardServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceViewModel;
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

/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CurrencyService currencyService;
    private final CurrencyDao currencyDao;
    private final UserDao userDao;

    /**
     * Constructor
     */
    @Autowired
    public CardServiceImpl(CardDao cardDao, ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder, CurrencyService currencyService,
                           CurrencyDao currencyDao, UserDao userDao) {
        this.cardDao = cardDao;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.currencyService = currencyService;
        this.currencyDao = currencyDao;
        this.userDao = userDao;
    }

    /**
     * @param cardServiceModel
     * @return
     */
    @Override
    public CardServiceViewModel createCard(CardServiceModel cardServiceModel) {
        Card card = mapCardServiceModelToCard(cardServiceModel);
        findCardByNumber(cardServiceModel);
        setCurrencyByCurrencyName(cardServiceModel, card);
        card.setDateIssued(LocalDateTime.now());
        card.setUsers(addUsersToCards(cardServiceModel));
        card.setPinCode(this.bCryptPasswordEncoder.encode(cardServiceModel.getPinCode()));
        this.cardDao.saveAndFlush(card);
        return mapCardToCardServiceViewModel(card);
    }

    /**
     * @param cardServiceModel
     * @return
     */
    @Transactional
    public List<User> addUsersToCards(CardServiceModel cardServiceModel) {
        List<User> userList = new ArrayList<>();
        cardServiceModel.getUsers().forEach(u -> {
            User user = userDao.findById(u.getId())
                    .orElseThrow(InvalidEntityException::new);
            userList.add(user);
        });
        return userList;
    }

    /**
     * @param cardServiceModel
     * @return
     */
    @Override
    @Transactional
    public CardServiceViewModel updateCard(CardServiceModel cardServiceModel) {
        Card card = mapCardServiceModelToCard(cardServiceModel);
        getCardById(cardServiceModel.getId());
        card.setUsers(addUsersToCards(cardServiceModel));
        this.cardDao.saveAndFlush(card);
        return mapCardToCardServiceViewModel(card);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CardServiceViewModel getCardById(long id) {
        return mapCardToCardServiceViewModel(getCardByIdFromRepository(id));
    }

    /**
     * @return
     */
    @Override
    public List<CardServiceViewModel> getAllCards() {
        validateIfCardsExists();
        List<Card> cards = cardDao.findAll();
        return mapCardListToCardServiceViewModelList(cards);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CardServiceViewModel deleteCardById(long id) {
        CardServiceViewModel cardServiceViewModel = this.getCardById(id);
        this.cardDao.deleteById(id);
        return cardServiceViewModel;

    }

    /**
     *
     */
    private void validateIfCardsExists() {
        this.cardDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Cards were found"));
    }

    /**
     * @param cards
     * @return
     */
    private List<CardServiceViewModel> mapCardListToCardServiceViewModelList(List<Card> cards) {
        return modelMapper.map(cards, new TypeToken<List<CardServiceViewModel>>() {
        }.getType());
    }

    /**
     * @param id
     * @return
     */
    private Card getCardByIdFromRepository(long id) {

        return this.cardDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Card  with ID %s not found.", id)));
    }

    /**
     * @param card
     * @return
     */
    private CardServiceViewModel mapCardToCardServiceViewModel(Card card) {
        return this.modelMapper.map(card, CardServiceViewModel.class);
    }

    /**
     * @param cardServiceModel
     * @return
     */
    private Card mapCardServiceModelToCard(CardServiceModel cardServiceModel) {
        return this.modelMapper.map(cardServiceModel, Card.class);
    }

    /**
     * @param cardServiceModel
     */
    private void findCardByNumber(CardServiceModel cardServiceModel) {
        this.cardDao.findByNumber(cardServiceModel.getNumber()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Card with number '%s' already exists.", cardServiceModel.getNumber()));
        });
    }

    /**
     * @param cardServiceModel
     * @param card
     */
    private void setCurrencyByCurrencyName(CardServiceModel cardServiceModel, Card card) {
        currencyDao.findByName(cardServiceModel.getCurrency().getName())
                .ifPresent(c -> {
                    card.setCurrency(this.modelMapper.map(getCurrencyServiceViewModel(cardServiceModel), Currency.class));
                });
    }

    /**
     * @param cardServiceModel
     * @return
     */
    private CurrencyServiceViewModel getCurrencyServiceViewModel(CardServiceModel cardServiceModel) {
        return this.currencyService.getCurrencyByName(cardServiceModel.getCurrency().getName());
    }

}

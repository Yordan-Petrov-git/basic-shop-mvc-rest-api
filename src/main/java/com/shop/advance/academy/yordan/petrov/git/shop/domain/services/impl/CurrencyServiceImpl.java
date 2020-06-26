package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Currency;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CurrencyServiceModel createCurrency(CurrencyServiceModel currencyServiceModel) {
        Currency currency = this.modelMapper.map(currencyServiceModel, Currency.class);
        return this.modelMapper.map( this.currencyRepository.saveAndFlush(currency), CurrencyServiceModel.class);
    }

    @Override
    public void updateCurrency(CurrencyServiceModel Currency) {

    }

    @Override
    public CurrencyServiceViewModel getCurrencyById(long id) {
        return this.modelMapper
                .map(this.currencyRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Currency  with ID %s not found.", id))), CurrencyServiceViewModel.class);
    }

    @Override
    public List<CurrencyServiceViewModel> getAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();

        return modelMapper.map(currencies, new TypeToken<List<CurrencyServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteCurrencyById(long id) {
        currencyRepository.deleteById(id);    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CurrencyServiceModel createCurrency(CurrencyServiceModel Currency) {
        return null;
    }

    @Override
    public void updateCurrency(CurrencyServiceModel Currency) {

    }

    @Override
    public CurrencyServiceViewModel getCurrencyById(long id) {
        return null;
    }

    @Override
    public List<CurrencyServiceViewModel> getAllCurrencies() {
        return null;
    }

    @Override
    public void deleteCurrencyById(long id) {

    }
}

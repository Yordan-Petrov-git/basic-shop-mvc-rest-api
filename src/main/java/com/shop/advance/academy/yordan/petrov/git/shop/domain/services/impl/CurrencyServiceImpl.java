package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Currency;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    public CurrencyServiceViewModel createCurrency(CurrencyServiceModel currencyServiceModel) {
        Currency currency = mapCurrencyServiceModelToCurrency(currencyServiceModel);
        findByName(currencyServiceModel);
        this.currencyRepository.saveAndFlush(currency);
        return mapCurrencyToCurrencyServiceViewModel(currency);
    }


    private Currency mapCurrencyServiceModelToCurrency(CurrencyServiceModel currencyServiceModel) {
        return this.modelMapper.map(currencyServiceModel, Currency.class);
    }

    @Override
    @Transactional
    public CurrencyServiceViewModel updateCurrency(CurrencyServiceModel currencyServiceModel) {
        Currency currency = mapCurrencyServiceModelToCurrency(currencyServiceModel);
        getCurrencyById(currencyServiceModel.getId());
        this.currencyRepository.saveAndFlush(currency);
        return mapCurrencyToCurrencyServiceViewModel(currency);

    }


    @Override
    public CurrencyServiceViewModel getCurrencyById(long id) {
        return this.modelMapper
                .map(this.currencyRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Currency  with ID %s not found.", id))), CurrencyServiceViewModel.class);
    }

    @Override
    public CurrencyServiceViewModel getCurrencyByName(String name) {
        return this.modelMapper
                .map(this.currencyRepository.findByName(name).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Currency  with name %s not found.", name))), CurrencyServiceViewModel.class);
    }

    @Override
    public List<CurrencyServiceViewModel> getAllCurrencies() {
        this.currencyRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Currencies were found"));
        List<Currency> currencies = currencyRepository.findAll();
        return modelMapper.map(currencies, new TypeToken<List<CurrencyServiceViewModel>>() {
        }.getType());
    }

    @Override
    public CurrencyServiceViewModel deleteCurrencyById(long id) {
        CurrencyServiceViewModel cityServiceViewModel = this.getCurrencyById(id);
        this.currencyRepository.deleteById(id);
        return cityServiceViewModel;
    }

    private void findByName(CurrencyServiceModel currencyServiceModel) {
        this.currencyRepository.findByName(currencyServiceModel.getName()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Currency with name '%s' already exists.", currencyServiceModel.getName()));
        });
    }

    private CurrencyServiceViewModel mapCurrencyToCurrencyServiceViewModel(Currency currency) {
        return this.modelMapper.map(currency, CurrencyServiceViewModel.class);
    }

}

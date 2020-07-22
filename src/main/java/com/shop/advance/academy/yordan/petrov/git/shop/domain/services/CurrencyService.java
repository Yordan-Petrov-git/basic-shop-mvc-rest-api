package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {

    CurrencyServiceViewModel createCurrency(CurrencyServiceModel Currency);

    CurrencyServiceViewModel updateCurrency(CurrencyServiceModel Currency);

    CurrencyServiceViewModel getCurrencyById(long id);

    List<CurrencyServiceViewModel> getAllCurrencies();

    CurrencyServiceViewModel deleteCurrencyById(long id);

    CurrencyServiceViewModel getCurrencyByName(String name);
}

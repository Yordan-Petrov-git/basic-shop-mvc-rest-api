package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CurrencyServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CurrencyService {
    CurrencyServiceModel createCurrency(CurrencyServiceModel Currency);

    void updateCurrency(CurrencyServiceModel Currency);

    CurrencyServiceViewModel getCurrencyById(long id);

    List<CurrencyServiceViewModel> getAllCurrencies();

    void deleteCurrencyById(long id);


}

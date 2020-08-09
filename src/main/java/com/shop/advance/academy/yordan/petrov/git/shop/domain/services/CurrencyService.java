package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface CurrencyService {

    CurrencyServiceViewModel createCurrency(CurrencyServiceModel Currency);

    CurrencyServiceViewModel updateCurrency(CurrencyServiceModel Currency);

    CurrencyServiceViewModel getCurrencyById(long id);

    List<CurrencyServiceViewModel> getAllCurrencies();

    CurrencyServiceViewModel deleteCurrencyById(long id);

    CurrencyServiceViewModel getCurrencyByName(String name);
}

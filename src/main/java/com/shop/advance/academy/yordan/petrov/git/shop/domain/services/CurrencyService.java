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

    /**
     * @param Currency
     * @return
     */
    CurrencyServiceViewModel createCurrency(CurrencyServiceModel Currency);

    /**
     * @param Currency
     * @return
     */
    CurrencyServiceViewModel updateCurrency(CurrencyServiceModel Currency);

    /**
     * @param id
     * @return
     */
    CurrencyServiceViewModel getCurrencyById(long id);

    /**
     * @return
     */
    List<CurrencyServiceViewModel> getAllCurrencies();

    /**
     * @param id
     * @return
     */
    CurrencyServiceViewModel deleteCurrencyById(long id);

    /**
     * @param name
     * @return
     */
    CurrencyServiceViewModel getCurrencyByName(String name);
}

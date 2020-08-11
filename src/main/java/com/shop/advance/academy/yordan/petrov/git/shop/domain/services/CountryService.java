package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceViewModel;
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
public interface CountryService {

    /**
     * @param Country
     * @return
     */
    CountryServiceViewModel createCountry(CountryServiceModel Country);

    /**
     * @param Country
     * @return
     */
    CountryServiceViewModel updateCountry(CountryServiceModel Country);

    /**
     * @param id
     * @return
     */
    CountryServiceViewModel getCountryById(long id);

    /**
     * @param name
     * @return
     */
    CountryServiceViewModel getCountryName(String name);

    /**
     * @return
     */
    List<CountryServiceViewModel> getAllCountries();

    /**
     * @param id
     * @return
     */
    CountryServiceViewModel deleteCountryById(long id);
}

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

    CountryServiceViewModel createCountry(CountryServiceModel Country);

    CountryServiceViewModel updateCountry(CountryServiceModel Country);

    CountryServiceViewModel getCountryById(long id);

    CountryServiceViewModel getCountryName(String name);

    List<CountryServiceViewModel> getAllCountries();

    CountryServiceViewModel deleteCountryById(long id);
}

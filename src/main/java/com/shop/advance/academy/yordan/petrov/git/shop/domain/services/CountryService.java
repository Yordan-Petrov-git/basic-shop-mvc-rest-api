package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    CountryServiceViewModel createCountry(CountryServiceModel Country);

    CountryServiceViewModel updateCountry(CountryServiceModel Country);

    CountryServiceViewModel getCountryById(long id);

    CountryServiceViewModel getCountryName(String name);

    List<CountryServiceViewModel> getAllCountries();

    CountryServiceViewModel deleteCountryById(long id);
}

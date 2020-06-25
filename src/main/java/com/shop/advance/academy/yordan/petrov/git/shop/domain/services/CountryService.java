package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CountryServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CountryServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CountryService {

    CountryServiceModel createCountry(CountryServiceModel Country);

    void updateCountry(CountryServiceModel Country);

    CountryServiceViewModel getCountryById(long id);

    List<CountryServiceViewModel> getAllCountries();

    void deleteCountryById(long id);


}

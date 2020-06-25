package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CountryRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CountryServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CountryServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountryServiceModel createCountry(CountryServiceModel Country) {
        return null;
    }

    @Override
    public void updateCountry(CountryServiceModel Country) {

    }

    @Override
    public CountryServiceViewModel getCountryById(long id) {
        return null;
    }

    @Override
    public List<CountryServiceViewModel> getAllCountries() {
        return null;
    }

    @Override
    public void deleteCountryById(long id) {

    }
}

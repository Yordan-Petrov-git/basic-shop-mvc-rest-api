package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CountryRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Country;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CountryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.persistence.EntityNotFoundException;
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
    public CountryServiceModel createCountry(CountryServiceModel countryServiceModel) {
        Country country = this.modelMapper.map(countryServiceModel, Country.class);
        return this.modelMapper.map( this.countryRepository.saveAndFlush(country),CountryServiceModel.class);
    }

    @Override
    public void updateCountry(CountryServiceModel countryServiceModel) {
        Country country = this.modelMapper.map(countryServiceModel, Country.class);
        this.modelMapper.map( this.countryRepository.saveAndFlush(country),CountryServiceModel.class);
    }

    @Override
    public CountryServiceViewModel getCountryById(long id) {
        return this.modelMapper
                .map(this.countryRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Country  with ID %s not found.", id))), CountryServiceViewModel.class);
    }

    @Override
    public List<CountryServiceViewModel> getAllCountries() {
        List<Country> countries = countryRepository.findAll();

        return modelMapper.map(countries, new TypeToken<List<CountryServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteCountryById(long id) {
        countryRepository.deleteById(id);
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CountryDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Country;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CountryService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao, ModelMapper modelMapper) {
        this.countryDao = countryDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountryServiceViewModel createCountry(CountryServiceModel countryServiceModel) {
        Country country = mapCountryServiceModelToCountry(countryServiceModel);
        findByNme(countryServiceModel);
        this.countryDao.saveAndFlush(country);
        return mapCountryToCountryServiceViewModel(country);
    }


    @Override
    @Transactional
    public CountryServiceViewModel updateCountry(CountryServiceModel countryServiceModel) {
        Country country = mapCountryServiceModelToCountry(countryServiceModel);
        getCountryById(countryServiceModel.getId());
        this.countryDao.saveAndFlush(country);
        return mapCountryToCountryServiceViewModel(country);
    }

    @Override
    public CountryServiceViewModel getCountryById(long id) {
        return this.modelMapper
                .map(this.countryDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Country  with ID %s not found.", id))), CountryServiceViewModel.class);
    }

    @Override
    public CountryServiceViewModel getCountryName(String name) {
        return this.modelMapper
                .map(this.countryDao.findByName(name).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Country  with name %s not found.", name))), CountryServiceViewModel.class);
    }

    @Override
    public List<CountryServiceViewModel> getAllCountries() {
        this.countryDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Countries were found"));
        List<Country> countries = countryDao.findAll();
        return modelMapper.map(countries, new TypeToken<List<CountryServiceViewModel>>() {
        }.getType());
    }

    @Override
    public CountryServiceViewModel deleteCountryById(long id) {
        CountryServiceViewModel countryServiceViewModel = this.getCountryById(id);
        this.countryDao.deleteById(id);
        return countryServiceViewModel;
    }

    private void findByNme(CountryServiceModel countryServiceModel) {
        this.countryDao.findByName(countryServiceModel.getName()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Country '%s' already exists.", countryServiceModel.getName()));
        });
    }

    private Country mapCountryServiceModelToCountry(CountryServiceModel countryServiceModel) {
        return this.modelMapper.map(countryServiceModel, Country.class);
    }

    private CountryServiceViewModel mapCountryToCountryServiceViewModel(Country country) {
        return this.modelMapper.map(country, CountryServiceViewModel.class);
    }
}

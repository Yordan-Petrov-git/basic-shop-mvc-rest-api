package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CityRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.City;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CityServiceViewModel createCity(CityServiceModel cityServiceModel) {

        City city = this.modelMapper.map(cityServiceModel, City.class);

        this.cityRepository.findCityByName(city.getName()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("City '%s' already exists.", city.getName()));

            //TODO
            // IF COUNTRY EXISTS ADD IT TO CITY IF NOT ADD IT TO DATABASE AND TO CITY
            // TO BE ABLE TO CREATE CITY ONLY IF COUNTRY EXISTS IN THE DATABASE

        });

        return this.modelMapper.map(this.cityRepository.saveAndFlush(city), CityServiceViewModel.class);
    }

    @Override
    @Transactional
    public CityServiceViewModel updateCity(long id) {

        City city = this.modelMapper.map(this.getCityById(id), City.class);

        CityServiceViewModel cityServiceViewModel = this.getCityById(id);

        this.cityRepository.saveAndFlush(city);

        return cityServiceViewModel;

    }


    @Override
    public CityServiceViewModel getCityByName(String name) {

        return this.modelMapper
                .map(this.cityRepository.findCityByName(name).orElseThrow(() ->
                        new EntityNotFoundException(String.format("City  with Name  %s not found.", name))), CityServiceViewModel.class);

    }

    @Override
    public CityServiceViewModel getCityById(long id) {

        return this.modelMapper
                .map(this.cityRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("City  with ID %s not found.", id))), CityServiceViewModel.class);

    }

    @Override
    public List<CityServiceViewModel> getAllCities() {

        this.cityRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No cities were found"));

        List<City> cities = this.cityRepository.findAll();

        return this.modelMapper.map(cities, new TypeToken<List<CityServiceViewModel>>() {
        }.getType());
    }

    @Override
    public CityServiceViewModel deleteCityById(long id) {

        CityServiceViewModel cityServiceViewModel = this.getCityById(id);

        this.cityRepository.deleteById(id);

        return cityServiceViewModel;
    }
}

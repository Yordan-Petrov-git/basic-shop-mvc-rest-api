package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CityRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.City;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public CityServiceModel createCity(CityServiceModel cityServiceModel) {
        City city = this.modelMapper.map(cityServiceModel, City.class);
        return this.modelMapper.map( this.cityRepository.saveAndFlush(city), CityServiceModel.class);
    }

    @Override
    public void updateCity(CityServiceModel cityServiceModel) {
        City city = this.modelMapper.map(cityServiceModel, City.class);
         this.modelMapper.map( this.cityRepository.saveAndFlush(city), CityServiceModel.class);
    }

    @Override
    public CityServiceViewModel getCityById(long id) {
        return this.modelMapper
                .map(this.cityRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("City  with ID %s not found.", id))), CityServiceViewModel.class);
    }

    @Override
    public List<CityServiceViewModel> getAllCities() {
        List<City> cities = cityRepository.findAll();

        return modelMapper.map(cities, new TypeToken<List<CityServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteCityById(long id) {
        cityRepository.deleteById(id);
    }
}

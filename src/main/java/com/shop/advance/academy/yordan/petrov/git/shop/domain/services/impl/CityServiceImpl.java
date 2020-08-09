package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CityDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CountryDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.City;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Country;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CityServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CityServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CountryService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;
    private final ModelMapper modelMapper;
    private final CountryService countryService;
    private final CountryDao countryDao;

    /**
     * Constructor
     */
    @Autowired
    public CityServiceImpl(CityDao cityDao, ModelMapper modelMapper,
                           CountryService countryService, CountryDao countryDao) {
        this.cityDao = cityDao;
        this.modelMapper = modelMapper;
        this.countryService = countryService;
        this.countryDao = countryDao;
    }

    /**
     * @param cityServiceModel
     * @return
     */
    @Override
    public CityServiceViewModel createCity(CityServiceModel cityServiceModel) {
        City city = mapCityServiceModelToCity(cityServiceModel);
        getCityByName(city);
        setCountryToCityByName(cityServiceModel, city);
        this.cityDao.saveAndFlush(city);
        return mapCityToCityServiceViewModel(city);
    }


    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public CityServiceViewModel updateCity(long id) {
        City city = this.modelMapper.map(this.getCityById(id), City.class);
        CityServiceViewModel cityServiceViewModel = this.getCityById(id);
        this.cityDao.saveAndFlush(city);
        return cityServiceViewModel;
    }


    /**
     * @param name
     * @return
     */
    @Override
    public CityServiceViewModel getCityByName(String name) {
        return this.modelMapper
                .map(this.cityDao.findCityByName(name).orElseThrow(() ->
                        new EntityNotFoundException(String.format("City  with Name  %s not found.", name))), CityServiceViewModel.class);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CityServiceViewModel getCityById(long id) {
        return this.modelMapper
                .map(this.cityDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("City  with ID %s not found.", id))), CityServiceViewModel.class);
    }

    /**
     * @return
     */
    @Override
    public List<CityServiceViewModel> getAllCities() {
        this.cityDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No cities were found"));

        List<City> cities = this.cityDao.findAll();
        return this.modelMapper.map(cities, new TypeToken<List<CityServiceViewModel>>() {
        }.getType());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CityServiceViewModel deleteCityById(long id) {
        CityServiceViewModel cityServiceViewModel = this.getCityById(id);
        this.cityDao.deleteById(id);
        return cityServiceViewModel;
    }

    /**
     * @param city
     */
    private void getCityByName(City city) {
        this.cityDao.findCityByName(city.getName()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("City '%s' already exists.", city.getName()));
        });
    }

    /**
     * @param cityServiceModel
     * @param city
     */
    private void setCountryToCityByName(CityServiceModel cityServiceModel, City city) {

        countryDao.findByName(getCityName(cityServiceModel))
                .ifPresent(c -> {
                    setCountryToCity(cityServiceModel, city);
                });
    }

    /**
     * @param cityServiceModel
     * @param city
     */
    private void setCountryToCity(CityServiceModel cityServiceModel, City city) {
        city.setCountry(mapToCountry(cityServiceModel));
    }

    /**
     * @param cityServiceModel
     * @return
     */
    private Country mapToCountry(CityServiceModel cityServiceModel) {
        return this.modelMapper.map(getCountryServiceViewModel(cityServiceModel), Country.class);
    }

    /**
     * @param cityServiceModel
     * @return
     */
    private String getCityName(CityServiceModel cityServiceModel) {
        return cityServiceModel.getCountry().getName();
    }

    /**
     * @param cityServiceModel
     * @return
     */
    private CountryServiceViewModel getCountryServiceViewModel(CityServiceModel cityServiceModel) {
        return this.countryService.getCountryName(getCityName(cityServiceModel));
    }

    /**
     * @param city
     * @return
     */
    private CityServiceViewModel mapCityToCityServiceViewModel(City city) {
        return this.modelMapper.map(city, CityServiceViewModel.class);
    }

    /**
     * @param cityServiceModel
     * @return
     */
    private City mapCityServiceModelToCity(CityServiceModel cityServiceModel) {
        return this.modelMapper.map(cityServiceModel, City.class);
    }
}

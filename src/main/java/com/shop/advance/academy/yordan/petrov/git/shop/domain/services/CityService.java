package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CityService {

    CityServiceModel createCity(CityServiceModel City);

    void updateCity(CityServiceModel City);

    CityServiceViewModel getCityById(long id);

    List<CityServiceViewModel> getAllCities();

    void deleteCityById(long id);


}

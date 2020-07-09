package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CityService {

    CityServiceViewModel createCity(CityServiceModel City);

    CityServiceViewModel updateCity(long id);

    CityServiceViewModel getCityById(long id);

    List<CityServiceViewModel> getAllCities();

    CityServiceViewModel deleteCityById(long id);

    CityServiceViewModel getCityByName(String name);
}

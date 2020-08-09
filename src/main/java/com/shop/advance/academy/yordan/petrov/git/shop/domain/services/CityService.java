package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CityServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CityServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface CityService {

    CityServiceViewModel createCity(CityServiceModel City);

    CityServiceViewModel updateCity(long id);

    CityServiceViewModel getCityById(long id);

    List<CityServiceViewModel> getAllCities();

    CityServiceViewModel deleteCityById(long id);

    CityServiceViewModel getCityByName(String name);
}

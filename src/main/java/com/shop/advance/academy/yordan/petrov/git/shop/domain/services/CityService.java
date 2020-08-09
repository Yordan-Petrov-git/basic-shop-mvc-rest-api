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

    /**
     * @param City
     * @return
     */
    CityServiceViewModel createCity(CityServiceModel City);

    /**
     * @param id
     * @return
     */
    CityServiceViewModel updateCity(long id);

    /**
     * @param id
     * @return
     */
    CityServiceViewModel getCityById(long id);

    /**
     * @return
     */
    List<CityServiceViewModel> getAllCities();

    /**
     * @param id
     * @return
     */
    CityServiceViewModel deleteCityById(long id);

    /**
     * @param name
     * @return
     */
    CityServiceViewModel getCityByName(String name);
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CityRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.City;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @MockBean
    CityRepository cityRepository;

    @Autowired
    CityService cityService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCityServiceReturnsAllCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());
        cities.add(new City());

        Mockito.when(cityRepository.findAll()).thenReturn(cities);
        List<CityServiceViewModel> cityServiceViewModels = cityService.getAllCities();

        assertEquals(3, cityServiceViewModels.size());
    }


    @Test
    public void testCityServiceGetCityById() {
        City city = new City();
        city.setId(15L);

        Mockito.when(cityRepository.findById(15L))
                .thenReturn(java.util.Optional.of(city));
        CityServiceViewModel cityServiceViewModel = this.modelMapper.map(city, CityServiceViewModel.class);

        assertEquals(cityServiceViewModel, cityService.getCityById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

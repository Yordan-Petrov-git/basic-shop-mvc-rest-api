package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CountryDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Country;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CountryServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CountryService;
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
public class CountryServiceTest {

    @MockBean
    CountryDao countryDao;

    @Autowired
    CountryService countryService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCountryServiceReturnsAllCountrys() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());
        countries.add(new Country());

        Mockito.when(countryDao.findAll()).thenReturn(countries);
        List<CountryServiceViewModel> countrysFetchedFromRepo = countryService.getAllCountries();

        assertEquals(3, countrysFetchedFromRepo.size());
    }


    @Test
    public void testCountryServiceGetCountryById() {
        Country country = new Country();
        country.setId(15L);

        Mockito.when(countryDao.findById(15L))
                .thenReturn(java.util.Optional.of(country));
        CountryServiceViewModel countryServiceViewModel = this.modelMapper.map(country, CountryServiceViewModel.class);

        assertEquals(countryServiceViewModel, countryService.getCountryById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Currency;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CurrencyServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
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
public class CurrencyServiceTest {

    @MockBean
    CurrencyDao currencyDao;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCurrencyServiceReturnsAllCurrencys() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency());
        currencies.add(new Currency());
        currencies.add(new Currency());

        Mockito.when(currencyDao.findAll()).thenReturn(currencies);
        List<CurrencyServiceViewModel> currencysFetchedFromRepo = currencyService.getAllCurrencies();

        assertEquals(3, currencysFetchedFromRepo.size());
    }


    @Test
    public void testCurrencyServiceGetCurrencyById() {
        Currency currency = new Currency();
        currency.setId(15L);

        Mockito.when(currencyDao.findById(15L))
                .thenReturn(java.util.Optional.of(currency));
        CurrencyServiceViewModel currencyServiceViewModel = this.modelMapper.map(currency, CurrencyServiceViewModel.class);

        assertEquals(currencyServiceViewModel, currencyService.getCurrencyById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.SellerDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Seller;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
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
public class SellerServiceTest {

    @MockBean
    SellerDao sellerDao;

    @Autowired
    SellerService sellerService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    //TODO ADD EXCEPTION TROW TEST IF SELLER IS SET TO USER WHO IS ALREADY A SELLER

    @Test
    public void testSellerServiceReturnsAllSellers() {
        List<Seller> sellers = new ArrayList<>();
        sellers.add(new Seller());
        sellers.add(new Seller());
        sellers.add(new Seller());

        Mockito.when(sellerDao.findAll()).thenReturn(sellers);
        List<SellerServiceViewModel> sellerServiceViewModels = sellerService.getAllSellers();

        assertEquals(3, sellerServiceViewModels.size());
    }


    @Test
    public void testSellerServiceGetSellerById() {
        Seller seller = new Seller();
        seller.setId(15L);

        Mockito.when(sellerDao.findById(15L))
                .thenReturn(java.util.Optional.of(seller));
        SellerServiceViewModel sellerServiceViewModel = this.modelMapper.map(seller, SellerServiceViewModel.class);

        assertEquals(sellerServiceViewModel, sellerService.getSellerById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

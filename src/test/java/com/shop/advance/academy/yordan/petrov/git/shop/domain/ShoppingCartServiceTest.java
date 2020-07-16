package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
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
public class ShoppingCartServiceTest {

    @MockBean
    ShoppingCartDao shoppingCartDao;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShoppingCartServiceReturnsAllShoppingCarts() {
        List<ShoppingCart> carts = new ArrayList<>();
        carts.add(new ShoppingCart());
        carts.add(new ShoppingCart());
        carts.add(new ShoppingCart());

        Mockito.when(shoppingCartDao.findAll()).thenReturn(carts);
        List<ShoppingCartServiceViewModel> transactionToAdd = shoppingCartService.getAllShoppingCarts();

        assertEquals(3, transactionToAdd.size());
    }


    @Test
    public void testShoppingCartServiceGetShoppingCartById() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(15L);

        Mockito.when(shoppingCartDao.findById(15L))
                .thenReturn(java.util.Optional.of(shoppingCart));
        ShoppingCartServiceViewModel shoppingCartServiceViewModel = this.modelMapper.map(shoppingCart, ShoppingCartServiceViewModel.class);

        assertEquals(shoppingCartServiceViewModel, shoppingCartService.getShoppingCartById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

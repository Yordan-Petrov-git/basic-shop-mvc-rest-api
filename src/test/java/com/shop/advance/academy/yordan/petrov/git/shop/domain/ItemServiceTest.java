package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.AddressService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @MockBean
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //TODO ADD TEST IF GETS ALL

    //TODO ADD TEST IF GETS  BY ID

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}
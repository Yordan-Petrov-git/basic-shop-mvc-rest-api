package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
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
/**
 * Class test  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @MockBean
    ItemDao itemDao;

    @Autowired
    ItemService itemService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testItemServiceReturnsAllItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());
        items.add(new Item());

        Mockito.when(itemDao.findAll()).thenReturn(items);
        List<ItemServiceViewModel> itemsFetchedFromRepo = itemService.getAllItems();

        assertEquals(3, itemsFetchedFromRepo.size());
    }


    @Test
    public void testItemServiceGetItemById() {
        Item item = new Item();
        item.setId(15L);

        Mockito.when(itemDao.findById(15L))
                .thenReturn(java.util.Optional.of(item));
        ItemServiceViewModel itemServiceViewModel = this.modelMapper.map(item, ItemServiceViewModel.class);

        assertEquals(itemServiceViewModel, itemService.getItemById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

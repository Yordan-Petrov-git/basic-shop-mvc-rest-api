package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.OrderRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Order;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
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
public class OrderServiceTest {

    @MockBean
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOrderServiceReturnsAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());

        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        List<OrderServiceViewModel> ordersFetchedFromRepo = orderService.getAllOrders();

        assertEquals(3, ordersFetchedFromRepo.size());
    }


    @Test
    public void testOrderServiceGetOrderById() {
        Order order = new Order();
        order.setId(15L);

        Mockito.when(orderRepository.findById(15L))
                .thenReturn(java.util.Optional.of(order));
        OrderServiceViewModel orderServiceViewModel = this.modelMapper.map(order, OrderServiceViewModel.class);

        assertEquals(orderServiceViewModel, orderService.getOrderById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

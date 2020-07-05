package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    OrderServiceViewModel createOrder(OrderServiceModel Order);

    OrderServiceViewModel updateOrder(OrderServiceModel Order);

    OrderServiceViewModel getOrderById(long id);

    List<OrderServiceViewModel> getAllOrders();

    OrderServiceViewModel deleteOrderById(long id);


}

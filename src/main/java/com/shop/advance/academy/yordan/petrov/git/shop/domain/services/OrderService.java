package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {

    OrderServiceModel createOrder(OrderServiceModel Order);

    void updateOrder(OrderServiceModel Order);

    OrderServiceViewModel getOrderById(long id);

    List<OrderServiceViewModel> getAllOrders();

    void deleteOrderById(long id);


}

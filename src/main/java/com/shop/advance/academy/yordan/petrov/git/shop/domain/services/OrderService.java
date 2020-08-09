package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OrderServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface OrderService {

    OrderServiceViewModel createOrder(OrderServiceModel Order);

    OrderServiceViewModel updateOrder(OrderServiceModel Order);

    OrderServiceViewModel getOrderById(long id);

    List<OrderServiceViewModel> getAllOrders();

    OrderServiceViewModel deleteOrderById(long id);
}

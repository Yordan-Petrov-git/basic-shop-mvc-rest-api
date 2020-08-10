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

    /**
     * @param Order
     * @return
     */
    OrderServiceViewModel createOrder(OrderServiceModel Order);

    /**
     * @param Order
     * @return
     */
    OrderServiceViewModel updateOrder(OrderServiceModel Order);

    /**
     * @param id
     * @return
     */
    OrderServiceViewModel getOrderById(long id);

    /**
     * @return
     */
    List<OrderServiceViewModel> getAllOrders();

    /**
     * @param id
     * @return
     */
    OrderServiceViewModel deleteOrderById(long id);
}

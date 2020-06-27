package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OrderRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Order;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderServiceModel createOrder(OrderServiceModel orderServiceModel) {
        Order order = this.modelMapper.map(orderServiceModel, Order.class);
        return this.modelMapper.map( this.orderRepository.saveAndFlush(order), OrderServiceModel.class);
    }

    @Override
    public void updateOrder(OrderServiceModel orderServiceModel) {
        Order order = this.modelMapper.map(orderServiceModel, Order.class);
         this.modelMapper.map( this.orderRepository.saveAndFlush(order), OrderServiceModel.class);
    }

    @Override
    public OrderServiceViewModel getOrderById(long id) {
        return this.modelMapper
                .map(this.orderRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Order  with ID %s not found.", id))), OrderServiceViewModel.class);

    }

    @Override
    public List<OrderServiceViewModel> getAllOrders() {

        this.orderRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Orders were found"));

        List<Order> orders = orderRepository.findAll();

        return modelMapper.map(orders, new TypeToken<List<OrderServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteOrderById(long id) {

        this.orderRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Order with id '%d' not found .", id)));

        this.orderRepository.deleteById(id);
    }
}

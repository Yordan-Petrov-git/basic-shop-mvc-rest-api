package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/register")
    public ResponseEntity<OrderServiceModel> createOrder(@RequestBody OrderServiceModel orderServiceModel) {
        orderService.createOrder(orderServiceModel);
        return new ResponseEntity<>(orderServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public void updateOrder(@PathVariable("id") Long id,@RequestBody OrderServiceModel orderServiceModel) {
        orderService.updateOrder(orderServiceModel);
    }


    @GetMapping("{id}")
    public OrderServiceViewModel getOrder(@PathVariable("id")final Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping()
    public List<OrderServiceViewModel> getOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
    }

}



package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
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


    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<OrderServiceViewModel> createOrder(@RequestBody OrderServiceModel orderServiceModel) {
        OrderServiceViewModel contactInformationServiceViewModel = orderService.createOrder(orderServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(OrderController.class, "createOrder", OrderServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(contactInformationServiceViewModel.getId()).toUri();
        log.info("Order  created : {} {}", contactInformationServiceViewModel, location);
        return ResponseEntity.created(location).body(contactInformationServiceViewModel);


    }

    @PutMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<OrderServiceViewModel> updateOrder(@RequestBody OrderServiceModel orderServiceModel) {
        OrderServiceViewModel contactInformationServiceViewModel = orderService.updateOrder(orderServiceModel);
        log.info("Order  UPDATED : {}", contactInformationServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactInformationServiceViewModel);
    }


    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<OrderServiceViewModel> getOrder(@PathVariable("id") final Long id) {
        OrderServiceViewModel contactInformationServiceViewModel = orderService.getOrderById(id);
        log.info("Order  found : {}", contactInformationServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(contactInformationServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<OrderServiceViewModel>> getOrders() {
        List<OrderServiceViewModel> orderServiceViewModels = orderService.getAllOrders();
        log.info("Sellers Found: {} ", orderServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(orderServiceViewModels);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_MODERATOR')")
    public ResponseEntity<OrderServiceViewModel> deleteOrder(@PathVariable("id") Long id) {
        OrderServiceViewModel contactInformationServiceViewModel = orderService.deleteOrderById(id);
        log.info("Order  deleted : {}", contactInformationServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactInformationServiceViewModel);

    }

}



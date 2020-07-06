package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OrderRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Order;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, ShoppingCartService shoppingCartService,
                            ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public OrderServiceViewModel createOrder(OrderServiceModel orderServiceModel) {

        Order order = this.modelMapper.map(orderServiceModel, Order.class);

        this.orderRepository.findByNumber(orderServiceModel.getNumber()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Order with number '%s' already exists.", orderServiceModel.getNumber()));
        });

        ShoppingCartServiceViewModel shoppingCartServiceViewModel = this.shoppingCartService.getShoppingCartById(orderServiceModel.getShoppingCart().getId());

        shoppingCartRepository.findById(orderServiceModel.getShoppingCart().getId())
                .ifPresent(c -> {
                    order.setShoppingCart(this.modelMapper.map(shoppingCartServiceViewModel, ShoppingCart.class));
                });

        // FIND ADDED ITEMS BY ID !!!! AND THEN GET THEM AND CALCULATE THE TOTAL PRICE MULTIPLY ALSO BY THEIR COUNT
        // FORMULA FOR TAXED A ((((TAX %/100)+1)*PRICE)  * QUANTITY)
        //  FORMULA WITH TAX B ((PRICE * QUANTITY) * (TAX %/100)+1))
        // FORMULA FOR  without tax (PRICE * QUANTITY)
        BigDecimal tax = orderServiceModel.getTax();
        Long itemId = orderServiceModel.getShoppingCart().getId();
        BigDecimal totalItemsPrice = this.shoppingCartService.getShoppingCartById(itemId).getTotalItemsPrice();
        BigDecimal taxInPercentage = calculateTaxPercentage(tax);
        order.setTotalPrice(calculateItemAfterTax(taxInPercentage, totalItemsPrice));

        return this.modelMapper.map(this.orderRepository.saveAndFlush(order), OrderServiceViewModel.class);
    }

    @Override
    @Transactional
    public OrderServiceViewModel updateOrder(OrderServiceModel orderServiceModel) {

        Order order = this.modelMapper.map(orderServiceModel, Order.class);

        this.orderRepository.findById(orderServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Order with id '%d' not found .", orderServiceModel.getId())));


        return this.modelMapper.map(this.orderRepository.saveAndFlush(order), OrderServiceViewModel.class);

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
    public OrderServiceViewModel deleteOrderById(long id) {

        OrderServiceViewModel orderServiceViewModel = this.getOrderById(id);

        this.orderRepository.deleteById(id);

        return orderServiceViewModel;
    }

    public BigDecimal calculateTaxPercentage(BigDecimal tax) {
        return tax.divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN).add(BigDecimal.valueOf(1));
    }

    public BigDecimal calculateItemAfterTax(BigDecimal taxPercentage, BigDecimal itemTotalPrice) {
        return taxPercentage.multiply(itemTotalPrice);

    }

}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OrderDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Order;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceViewModel;
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
/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final ModelMapper modelMapper;
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartDao shoppingCartDao;

    /**
     * Constructor
     */
    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ModelMapper modelMapper, ShoppingCartService shoppingCartService,
                            ShoppingCartDao shoppingCartDao) {
        this.orderDao = orderDao;
        this.modelMapper = modelMapper;
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public OrderServiceViewModel createOrder(OrderServiceModel orderServiceModel) {

        Order order = mapOrderServiceModelToOrder(orderServiceModel);
        findOrderByNumber(orderServiceModel);
        setShoppingCartOrder(orderServiceModel, order);

        //TODO ADD PROMO CODE SYSTEM AND DISCOUNTS IN % SYSTEM !!!
        //TODO discount = (TOTAL PRICE AETHER TAXES)-((rate%/100)*price before tax )
        //TODO discount code name == discount rate in % fixed store in db !!!

        //TODO ADD SHIPPING BASED ON  ITEM WEIGHT AND SHIPPING TYPE
        //TODO  PICK UP FROM STORE FREE
        // TODO STANDARD 4.50 BASE FOR 1 KG + 1 BGN ON EVERY KG // 2 KG 5,50 // 3KG 6.50 ... ((BASE(4.50))+(KG*1))
        //TODO can have free shipping if total cost of order is above 100 in total

        // FIND ADDED ITEMS BY ID !!!! AND THEN GET THEM AND CALCULATE THE TOTAL PRICE MULTIPLY ALSO BY THEIR COUNT
        // FORMULA FOR TAXED A ((((TAX %/100)+1)*PRICE)  * QUANTITY)
        //  FORMULA WITH TAX B ((PRICE * QUANTITY) * (TAX %/100)+1))
        // FORMULA FOR  without tax (PRICE * QUANTITY)
        BigDecimal tax = orderServiceModel.getTax();
        Long itemId = orderServiceModel.getShoppingCart().getId();
        BigDecimal totalItemsPrice = this.shoppingCartService.getShoppingCartById(itemId).getTotalItemsPrice();
        BigDecimal taxInPercentage = calculateTaxPercentage(tax);
        order.setTotalPrice(calculateItemAfterTax(taxInPercentage, totalItemsPrice));
        this.orderDao.saveAndFlush(order);
        return mapOrderToOrderServiceModel(order);
    }

    @Override
    @Transactional
    public OrderServiceViewModel updateOrder(OrderServiceModel orderServiceModel) {
        Order order = mapOrderServiceModelToOrder(orderServiceModel);
        getOrderById(orderServiceModel.getId());
        this.orderDao.saveAndFlush(order);
        return mapOrderToOrderServiceModel(order);
    }

    @Override
    public OrderServiceViewModel getOrderById(long id) {
        return mapOrderToOrderServiceModel(findOrderById(id));
    }

    @Override
    public List<OrderServiceViewModel> getAllOrders() {
        validateIfFoundAnyOrders();
        List<Order> orders = findAllOrders();
        return mapListOrderToListOrderServiceViewModel(orders);
    }

    @Override
    public OrderServiceViewModel deleteOrderById(long id) {
        OrderServiceViewModel orderServiceViewModel = this.getOrderById(id);
        this.orderDao.deleteById(id);
        return orderServiceViewModel;
    }

    public BigDecimal calculateTaxPercentage(BigDecimal tax) {
        return tax.divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN).add(BigDecimal.valueOf(1));
    }

    public BigDecimal calculateItemAfterTax(BigDecimal taxPercentage, BigDecimal itemTotalPrice) {
        return taxPercentage.multiply(itemTotalPrice);

    }

    public void setShoppingCartOrder(OrderServiceModel orderServiceModel, Order order) {
        shoppingCartDao.findById(orderServiceModel.getShoppingCart().getId())
                .ifPresent(c -> {
                    order.setShoppingCart(mapOrderServiceModelToShoppingCart(orderServiceModel));
                });
    }

    public ShoppingCart mapOrderServiceModelToShoppingCart(OrderServiceModel orderServiceModel) {
        return this.modelMapper.map(getShoppingCartServiceViewModel(orderServiceModel), ShoppingCart.class);
    }

    public ShoppingCartServiceViewModel getShoppingCartServiceViewModel(OrderServiceModel orderServiceModel) {
        return this.shoppingCartService.getShoppingCartById(orderServiceModel.getShoppingCart().getId());
    }

    public void findOrderByNumber(OrderServiceModel orderServiceModel) {
        this.orderDao.findByNumber(orderServiceModel.getNumber()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Order with number '%s' already exists.", orderServiceModel.getNumber()));
        });
    }

    public Order mapOrderServiceModelToOrder(OrderServiceModel orderServiceModel) {
        return this.modelMapper.map(orderServiceModel, Order.class);
    }

    public OrderServiceViewModel mapOrderToOrderServiceModel(Order order) {
        return this.modelMapper.map(order, OrderServiceViewModel.class);
    }

    public Order findOrderById(long id) {
        return this.orderDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Order  with ID %s not found.", id)));
    }

    public List<OrderServiceViewModel> mapListOrderToListOrderServiceViewModel(List<Order> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderServiceViewModel>>() {
        }.getType());
    }

    public void validateIfFoundAnyOrders() {
        findOrders()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Orders were found"));
    }

    private List<Order> findOrders() {
        return this.orderDao.findAll();
    }

    public List<Order> findAllOrders() {
        return findOrders();
    }
}

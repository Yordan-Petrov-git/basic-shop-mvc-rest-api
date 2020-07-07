package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OrderRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Order;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.OrderStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.PaymentType;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.ShipmentType;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.TransactionStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.PurchasingService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.TransactionService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalCardTransactionOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Service
public class PurchasingServiceImpl implements PurchasingService {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;

    @Autowired
    public PurchasingServiceImpl(OrderService orderService, OrderRepository orderRepository, ModelMapper modelMapper, TransactionService transactionService) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.transactionService = transactionService;
    }


    @Override
    public TransactionServiceViewModel payByCard(TransactionServiceModel transaction) {

        Order order = orderRepository.findById(transaction.getOrder().getId())
                .orElseThrow();

        transaction.setAmount(order.getTotalPrice());
        //Fixed fee for purchase
        transaction.setFee(BigDecimal.valueOf(5.00));
        transaction.setDescription("Item purchase");
        transactionService.createTransaction(transaction);

        return this.modelMapper.map(transaction, TransactionServiceViewModel.class);
    }

    @Override
    public TransactionServiceViewModel refundCardPurchase(TransactionServiceModel transaction) {
        //Refund if only within of 14 days of transaction
        Instant currentTime = Instant.now();
        TransactionServiceModel transactionServiceForRefund =
                modelMapper.map(this.transactionService.getTransactionById(transaction.getId())
                        , TransactionServiceModel.class);

        TransactionStatus transactionStatus = transactionServiceForRefund.getTransactionStatus();

        if (transactionStatus == TransactionStatus.REFUNDED) {
            throw new IllegalCardTransactionOperation("The transaction is already refunded");
        }

        Instant dateTransactionCompleted = transactionServiceForRefund.getDateCompleted();

        int minutes = Duration.between(dateTransactionCompleted, currentTime).toSecondsPart();

        int daysInSecondsBetweenPurchaseAndTransaction = 1209600;

        if (minutes >= daysInSecondsBetweenPurchaseAndTransaction) {
            throw new IllegalCardTransactionOperation("The order cannot be refunded due to 14 days have passed from the purchase till now");
        }

        Order order = orderRepository.findById(transaction.getOrder().getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("No Order have been found"));

        //TODO ADD ORDER STATUS CANCELED AND EDIT IT
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setShipmentType(ShipmentType.NONE);
        order.setPaymentType(PaymentType.BY_CARD);
        //Update
        OrderServiceModel orderServiceModel = this.modelMapper.map(order, OrderServiceModel.class);
        orderService.updateOrder(orderServiceModel);

        transactionServiceForRefund.setTransactionStatus(TransactionStatus.REFUNDED);
        transactionServiceForRefund.setDescription("Refunded for item");
        transactionServiceForRefund.setDateUpdated(Instant.now());

        transactionService.refundTransactionById(transaction.getId());
        transactionService.updateTransaction(transactionServiceForRefund);

        return this.modelMapper.map(transactionServiceForRefund, TransactionServiceViewModel.class);
    }

    public boolean isTimeBetweenTwoDatesGreaterOrEqualToSetDaysInSeconds(Instant dateTransactionCompleted) {
        boolean isTimeForRefundDue = true;
        //14 days in seconds
        int daysInSecondsBetweenPurchaseAndTransaction = 1209600;
        Instant currentTime = Instant.now();

        int minutes = Duration
                .between(dateTransactionCompleted, currentTime)
                .toSecondsPart();

        if (minutes >= daysInSecondsBetweenPurchaseAndTransaction) {
            isTimeForRefundDue = true;
            throw new IllegalCardTransactionOperation("The order cannot be refunded due to 14 days have passed from the purchase till now");
        } else {
            isTimeForRefundDue = false;
        }

        return isTimeForRefundDue;
    }
}

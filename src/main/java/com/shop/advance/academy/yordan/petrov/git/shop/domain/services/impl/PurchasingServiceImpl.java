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

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;

    @Autowired
    public PurchasingServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper,
                                 TransactionService transactionService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.transactionService = transactionService;
    }

    @Override
    public TransactionServiceViewModel payByCard(TransactionServiceModel transactionServiceModel) {
        Order order = findOrderFromTransactionServiceModelById(transactionServiceModel);
        isOrderStatusCanceled(order.getOrderStatus());
        isOrderStatusFinished(order.getOrderStatus());
        isOrderStatusProccesing(order.getOrderStatus());
        transactionServiceModel.setAmount(order.getTotalPrice());
        transactionServiceModel.setFee(BigDecimal.valueOf(5.00));
        transactionServiceModel.setDescription("Item purchase");
        updateOrderForPurchase(order);
        createTransactionForPayByCard(transactionServiceModel);
        //TODO ADD VIP STATIS TO CUSTOMER ON CERTAIN SPENDING AMMOUNT
        return mapTransactionServiceModelToTransactionServiceViewModel(transactionServiceModel);
    }


    @Override
    public TransactionServiceViewModel refundCardPurchase(TransactionServiceModel transactionServiceModel) {
        //TODO GET SENDER AND RECIER FORM TRANSACTION
        TransactionServiceModel transactionServiceForRefund = getTransactionServiceModelForRefundTransaction(transactionServiceModel);
        refundCardPurchaseValidationAndUpdates(transactionServiceModel, transactionServiceForRefund);
        return mapTransactionServiceModelToTransactionServiceViewModel(transactionServiceForRefund);
    }

    private void createTransactionForPayByCard(TransactionServiceModel transactionServiceModel) {
        transactionService.createTransaction(transactionServiceModel);
    }


    private void refundCardPurchaseValidationAndUpdates(TransactionServiceModel transactionServiceModel
            , TransactionServiceModel transactionServiceForRefund) {

        isTransactionStatusRefunded(transactionServiceForRefund.getTransactionStatus());
        isTimeBetweenTwoDatesGreaterOrEqualToSetDaysInSeconds(transactionServiceForRefund.getDateCompleted());
        updateOrderForRefund(findOrderFromTransactionServiceModelById(transactionServiceModel));
        transactionServiceForRefund.setTransactionStatus(TransactionStatus.REFUNDED);
        transactionServiceForRefund.setDescription("Refunded for item");
        transactionServiceForRefund.setDateUpdated(Instant.now());
        refundTransactionById(transactionServiceModel);
        updateTransactionService(transactionServiceForRefund);
    }


    private void refundTransactionById(TransactionServiceModel transactionServiceModel) {
        this.transactionService.refundTransactionById(transactionServiceModel.getId());
    }

    public void isTimeBetweenTwoDatesGreaterOrEqualToSetDaysInSeconds(Instant dateTransactionCompleted) {
        boolean isNonRefundable;
        //14 days in seconds
        int daysInSecondsBetweenPurchaseAndTransaction = 1209600;
        int minutes = Duration
                .between(dateTransactionCompleted, Instant.now())
                .toSecondsPart();
        isNonRefundable = minutes >= daysInSecondsBetweenPurchaseAndTransaction;
        if (isNonRefundable) {
            throw new IllegalCardTransactionOperation("The order cannot be refunded due to 14 days have passed from the purchase till now");
        }
    }

    public void isTransactionStatusRefunded(TransactionStatus transactionStatus) {
        boolean isNonRefundable = false;
        isNonRefundable = transactionStatus == TransactionStatus.REFUNDED;
        if (isNonRefundable) {
            throw new IllegalCardTransactionOperation("The transaction is already refunded");
        }
    }

    public void isOrderStatusCanceled(OrderStatus orderStatus) {
        boolean isOrderNonCanceled = false;
        isOrderNonCanceled = orderStatus == OrderStatus.CANCELED;
        if (isOrderNonCanceled) {
            throw new IllegalCardTransactionOperation("The Order is Canceled");
        }
    }

    public void isOrderStatusFinished(OrderStatus orderStatus) {
        boolean isOrderNonCanceled = false;
        isOrderNonCanceled = orderStatus == OrderStatus.PICKED_UP_BY;
        if (isOrderNonCanceled) {
            throw new IllegalCardTransactionOperation("The Order is finished");
        }
    }

    public void isOrderStatusProccesing(OrderStatus orderStatus) {
        boolean isOrderNonCanceled = false;
        isOrderNonCanceled = orderStatus == OrderStatus.PROCESSING;
        if (isOrderNonCanceled) {
            throw new IllegalCardTransactionOperation("The Order is proccesing");
        }
    }

    public Order findOrderFromTransactionServiceModelById(TransactionServiceModel transaction) {
        return orderRepository.findById(transaction.getOrder().getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("No Order have been found"));
    }

    public void updateOrderForRefund(Order order) {
        order.setOrderStatus(OrderStatus.CANCELED);
    }

    public void updateOrderForPurchase(Order order) {
        order.setShipmentType(ShipmentType.NONE);
        order.setPaymentType(PaymentType.BY_CARD);
        order.setOrderStatus(OrderStatus.PROCESSING);
    }

    public OrderServiceModel mapOrderToOrderServiceModel(Order order) {
        return this.modelMapper.map(order, OrderServiceModel.class);
    }

    public void updateTransactionService(TransactionServiceModel transactionServiceModel) {
        transactionService.updateTransaction(transactionServiceModel);
    }

    public TransactionServiceModel getTransactionServiceModelForRefundTransaction(TransactionServiceModel transactionServiceModel) {
        return mapTransactionServiceViewModelToTransactionServiceModel(this.transactionService.getTransactionById(transactionServiceModel.getId()));
    }

    public TransactionServiceModel mapTransactionServiceViewModelToTransactionServiceModel(TransactionServiceViewModel transactionServiceViewModel) {
        return this.modelMapper.map(transactionServiceViewModel, TransactionServiceModel.class);
    }

    public TransactionServiceViewModel mapTransactionServiceModelToTransactionServiceViewModel(TransactionServiceModel transactionServiceModel) {
        return this.modelMapper.map(transactionServiceModel, TransactionServiceViewModel.class);
    }

}

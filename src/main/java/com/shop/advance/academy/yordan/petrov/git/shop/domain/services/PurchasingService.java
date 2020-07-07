package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Order;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceViewModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
@Service
public interface PurchasingService {

    // pay for the order by card
    // get order total price and cardBuyer ->  cardSeller
    //make new transaction

   TransactionServiceViewModel payByCard(TransactionServiceModel transaction);

    TransactionServiceViewModel refundCardPurchase(TransactionServiceModel transaction);
}

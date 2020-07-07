package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceViewModel;
import org.springframework.stereotype.Service;

@Service
public interface PurchasingService {

    TransactionServiceViewModel payByCard(TransactionServiceModel transaction);

    TransactionServiceViewModel refundCardPurchase(TransactionServiceModel transaction);
}

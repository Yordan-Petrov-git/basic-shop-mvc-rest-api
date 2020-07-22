package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceViewModel;
import org.springframework.stereotype.Service;

@Service
public interface PurchasingService {

    TransactionServiceViewModel payByCard(TransactionServiceModel transaction);

    TransactionServiceViewModel refundCardPurchase(TransactionServiceModel transaction);
}

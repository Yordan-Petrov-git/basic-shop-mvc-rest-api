package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TransactionOperationService {

    void withdrawMoney(Long id, BigDecimal amount);

    void depositMoney(Long id, BigDecimal amount);

    void transferMoney(Long RecipientId, Long SenderId, BigDecimal amount);

    void refund(Long toId, Long fromId, BigDecimal amount);
}

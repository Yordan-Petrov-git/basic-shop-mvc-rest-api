package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public interface TransactionOperationService {

    void withdrawMoney(long id, BigDecimal amount);

    void depositMoney(long id, BigDecimal amount);

    void transferMoney(long toId, long fromId, BigDecimal amount);

}

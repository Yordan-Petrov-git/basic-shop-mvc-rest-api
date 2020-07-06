package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public TransactionServiceViewModel createTransaction(TransactionServiceModel transactionServiceModel) {
        //TODO ADD IMPLEMENTATION
        return null;
    }

    @Override
    public TransactionServiceViewModel updateTransaction(TransactionServiceModel transactionServiceModel) {
        return null; //TODO ADD IMPLEMENTATION
    }

    @Override
    public TransactionServiceViewModel getTransactionById(Long id) {
        return null; //TODO ADD IMPLEMENTATION
    }

    @Override
    public List<TransactionServiceViewModel> getAllTransactions() {
        return null; //TODO ADD IMPLEMENTATION
    }

    @Override
    public TransactionServiceViewModel deleteTransactionById(Long id) {
        return null; //TODO ADD IMPLEMENTATION
    }

    @Override
    public void withdrawMoney(long id, BigDecimal amount) {
        //TODO ADD IMPLEMENTATION
    }

    @Override
    public void depositMoney(long id, BigDecimal amount) {
        //TODO ADD IMPLEMENTATION
    }

    @Override
    public void transferMoney(long toId, long fromId, BigDecimal amount) {
        //TODO ADD IMPLEMENTATION
    }
}

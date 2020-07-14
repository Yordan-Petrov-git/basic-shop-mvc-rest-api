package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TransactionService extends TransactionOperationService {

    TransactionServiceViewModel createTransaction(TransactionServiceModel transactionServiceModel);

    TransactionServiceViewModel updateTransaction(TransactionServiceModel transactionServiceModel);

    TransactionServiceViewModel getTransactionById(Long id);

    List<TransactionServiceViewModel> getAllTransactions();

    TransactionServiceViewModel deleteTransactionById(Long id);

    TransactionServiceViewModel refundTransactionById(Long id);
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface service for transaction.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface TransactionService extends TransactionOperationService {

    /**
     * @param transactionServiceModel
     * @return
     */
    TransactionServiceViewModel createTransaction(TransactionServiceModel transactionServiceModel);

    /**
     * @param transactionServiceModel
     * @return
     */
    TransactionServiceViewModel updateTransaction(TransactionServiceModel transactionServiceModel);

    /**
     * @param id
     * @return
     */
    TransactionServiceViewModel getTransactionById(Long id);

    /**
     * @return
     */
    List<TransactionServiceViewModel> getAllTransactions();

    /**
     * @param id
     * @return
     */
    TransactionServiceViewModel deleteTransactionById(Long id);

    /**
     * @param id
     * @return
     */
    TransactionServiceViewModel refundTransactionById(Long id);
}

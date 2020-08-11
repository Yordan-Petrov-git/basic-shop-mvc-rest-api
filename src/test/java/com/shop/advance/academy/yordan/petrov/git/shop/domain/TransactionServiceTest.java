package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.TransactionDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.TransactionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Class test  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @MockBean
    TransactionDao transactionDao;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testTransactionServiceReturnsAllTransactions() {
        List<Transaction> transactionToAdd = new ArrayList<>();
        transactionToAdd.add(new Transaction());
        transactionToAdd.add(new Transaction());
        transactionToAdd.add(new Transaction());

        Mockito.when(transactionDao.findAll()).thenReturn(transactionToAdd);
        List<TransactionServiceViewModel> transactionsFetchedFromRepo = transactionService.getAllTransactions();

        assertEquals(3, transactionsFetchedFromRepo.size());
    }


    @Test
    public void testTransactionServiceGetTransactionById() {
        Transaction transaction = new Transaction();
        transaction.setId(15L);

        Mockito.when(transactionDao.findById(15L))
                .thenReturn(java.util.Optional.of(transaction));
        TransactionServiceViewModel transactionServiceViewModel = this.modelMapper.map(transaction, TransactionServiceViewModel.class);

        assertEquals(transactionServiceViewModel, transactionService.getTransactionById(15L));
    }


    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

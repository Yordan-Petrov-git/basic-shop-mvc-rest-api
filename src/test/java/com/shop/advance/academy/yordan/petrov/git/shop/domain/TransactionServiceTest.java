package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.TransactionRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.TransactionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @MockBean
    TransactionRepository transactionRepository;

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

        Mockito.when(transactionRepository.findAll()).thenReturn(transactionToAdd);
        List<TransactionServiceViewModel> TransactionsFetchedFromRepo = transactionService.getAllTransactions();

        assertEquals(3, TransactionsFetchedFromRepo.size());
    }

    //TODO ADD TEST IF GETS  BY ID

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}

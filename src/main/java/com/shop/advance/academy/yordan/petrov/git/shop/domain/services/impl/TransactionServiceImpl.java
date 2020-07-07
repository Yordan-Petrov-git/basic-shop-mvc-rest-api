package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CardRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.TransactionRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.TransactionStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.TransactionService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalCardTransactionOperation;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final CardService cardService;
    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CardRepository cardRepository, CardService cardService, CurrencyService currencyService, CurrencyRepository currencyRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.cardService = cardService;
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TransactionServiceViewModel createTransaction(TransactionServiceModel transactionServiceModel) {

        Transaction transaction = this.modelMapper.map(transactionServiceModel, Transaction.class);

        //Sender
        CardServiceViewModel cardServiceViewModelSender = this.cardService.getCardById(transactionServiceModel.getSender().getId());

        cardRepository.findById(transactionServiceModel.getSender().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setSender(this.modelMapper.map(cardServiceViewModelSender, CardServiceModel.class));
                });
        //Recipient
        CardServiceViewModel cardServiceViewModelRecipient = this.cardService.getCardById(transactionServiceModel.getRecipient().getId());

        cardRepository.findById(transactionServiceModel.getRecipient().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setRecipient(this.modelMapper.map(cardServiceViewModelRecipient, CardServiceModel.class));
                });

        //Currency
        CurrencyServiceViewModel currencyServiceViewModel = this.currencyService.getCurrencyById(transactionServiceModel.getCurrency().getId());
        currencyRepository.findById(transactionServiceModel.getCurrency().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setCurrency(this.modelMapper.map(currencyServiceViewModel, CurrencyServiceModel.class));
                });

        //TODO ADD EXCHANGE RATE EVENTUALLY IF THE RECEIVER CARD IS IN DIFFERENT CURRENCY
        BigDecimal fee = transactionServiceModel.getFee();
        Long idRecipient = (transactionServiceModel.getRecipient().getId());
        BigDecimal amount = transactionServiceModel.getAmount();
        Long idSender =  (transactionServiceModel.getSender().getId());

        //Withdraw for the fee of the transaction
        withdrawMoney(idSender,fee);
        //Transaction method
        transferMoney(idRecipient,idSender , amount);
        transaction.setDateCreated(Instant.now());
        transaction.setDateCompleted(Instant.now());
        transaction.setDateUpdated(Instant.now());
        transaction.setTransactionStatus(TransactionStatus.CONFIRMED);
        System.out.println();
        return this.modelMapper.map(this.transactionRepository.saveAndFlush(transaction), TransactionServiceViewModel.class);
    }

    @Override
    @Transactional
    public TransactionServiceViewModel updateTransaction(TransactionServiceModel transactionServiceModel) {

        Transaction transaction = this.modelMapper.map(transactionServiceModel, Transaction.class);


        this.transactionRepository.findById(transactionServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Transaction with id '%d' not found .", transactionServiceModel.getId())));

        return this.modelMapper.map(this.transactionRepository.saveAndFlush(transaction), TransactionServiceViewModel.class);

    }

    @Override
    public TransactionServiceViewModel getTransactionById(Long id) {

        return this.modelMapper
                .map(this.transactionRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Transaction  with ID %s not found.", id))), TransactionServiceViewModel.class);

    }

    @Override
    public List<TransactionServiceViewModel> getAllTransactions() {

        List<Transaction> transactions = transactionRepository.findAll();

        return modelMapper.map(transactions, new TypeToken<List<TransactionServiceViewModel>>() {
        }.getType());
    }

    @Override
    @Transactional
    public TransactionServiceViewModel deleteTransactionById(Long id) {

        this.transactionRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Transaction with id '%d' not found .", id)));

        TransactionServiceViewModel deleteTransaction = this.getTransactionById(id);

        this.transactionRepository.deleteById(id);

        return this.modelMapper.map(deleteTransaction, TransactionServiceViewModel.class);
    }

    @Override
    @Transactional
    public void withdrawMoney(Long id, BigDecimal amount) {

        Card card = cardRepository.findById(id)
                .orElseThrow();

        if (card.getBalance().compareTo(amount) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Current balance of card with number : %s is : %.2f and it is not sufficient to withdraw amount:  %.2f",
                            card.getNumber(),card.getBalance(),amount));
        }

        card.setBalance(card.getBalance().subtract(amount));
        cardService.updateCard(this.modelMapper.map(card,CardServiceModel.class));
    }

    @Override
    @Transactional
    public void depositMoney(Long id, BigDecimal amount) {

        Card card = cardRepository.findById(id)
                .orElseThrow();

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Deposit amount cannot be : %.2f",
                            amount));
        }

        card.setBalance(card.getBalance().add(amount));
        cardService.updateCard(this.modelMapper.map(card,CardServiceModel.class));
    }

    @Override
    @Transactional
    public void transferMoney(Long toId, Long fromId, BigDecimal amount) {

        depositMoney(toId, amount);
        withdrawMoney(fromId, amount);

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Transfer amount cannot be : %.2f",
                            amount));
        }
    }

}

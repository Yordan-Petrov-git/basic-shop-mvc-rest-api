package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CardRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OrderRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.TransactionRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.TransactionStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CurrencyService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OrderService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.TransactionService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalCardTransactionOperation;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

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
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CardRepository cardRepository,
                                  CardService cardService, CurrencyService currencyService, CurrencyRepository currencyRepository,
                                  ModelMapper modelMapper, OrderService orderService, OrderRepository orderRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.cardService = cardService;
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @Override
    public TransactionServiceViewModel createTransaction(TransactionServiceModel transactionServiceModel) {
        //TODO ADD EXCHANGE RATE EVENTUALLY IF THE RECEIVER CARD IS IN DIFFERENT CURRENCY
        Transaction transaction = mapTransactionServiceModelToTransaction(transactionServiceModel);
        setUpTransactionRouts(transactionServiceModel);
        Long idSender = (transactionServiceModel.getSender().getId());
        this.withdrawMoney(idSender, transactionServiceModel.getFee());
        this.transferMoney(transactionServiceModel.getRecipient().getId(), idSender, transactionServiceModel.getAmount());
        transaction.setDateCreated(Instant.now());
        transaction.setDateCompleted(Instant.now());
        transaction.setDateUpdated(Instant.now());
        transaction.setTransactionStatus(TransactionStatus.CONFIRMED);
        return mapTransactionToTransactionServiceViewModel(this.transactionRepository.saveAndFlush(transaction));
    }

    @Override
    @Transactional
    public TransactionServiceViewModel updateTransaction(TransactionServiceModel transactionServiceModel) {
        Transaction transaction = mapTransactionServiceModelToTransaction(transactionServiceModel);
        getTransactionId(transactionServiceModel.getId());
        return mapTransactionToTransactionServiceViewModel(this.transactionRepository.saveAndFlush(transaction));
    }

    @Override
    public TransactionServiceViewModel getTransactionById(Long id) {
        return mapTransactionToTransactionServiceViewModel(this.getTransactionId(id));
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
        Transaction transaction = getTransactionId(id);
        this.transactionRepository.deleteById(id);
        return mapTransactionToTransactionServiceViewModel(transaction);
    }

    @Override
    @Transactional
    public TransactionServiceViewModel refundTransactionById(Long id) {
        Transaction transaction = getTransactionId(id);
        this.refund(transaction.getRecipient().getId(), transaction.getSender().getId(), transaction.getAmount());
        this.updateTransaction(mapTransactionToTransactionServiceModel(transaction));
        return mapTransactionToTransactionServiceViewModel(transaction);
    }


    @Override
    @Transactional
    public void withdrawMoney(Long id, BigDecimal amount) {
        Card card = getCardId(id);
        if (card.getBalance().compareTo(amount) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Current balance of card with number : %s is : %.2f and it is not sufficient to withdraw amount:  %.2f",
                            card.getNumber(), card.getBalance(), amount));
        }
        card.setBalance(card.getBalance().subtract(amount));
        cardServiceUpdate(card);
    }


    @Override
    @Transactional
    public void depositMoney(Long id, BigDecimal amount) {
        Card card = getCardId(id);
        checksIfDepositAmountIsZero(amount);
        card.setBalance(card.getBalance().add(amount));
        cardServiceUpdate(card);
    }


    @Override
    @Transactional
    public void transferMoney(Long toId, Long fromId, BigDecimal amount) {
        depositMoney(toId, amount);
        withdrawMoney(fromId, amount);
        checksIfTransferAmountIsZero(amount);
    }

    @Override
    @Transactional
    public void refund(Long toId, Long fromId, BigDecimal amount) {
        depositMoney(fromId, amount);
        withdrawMoney(toId, amount);
        checksIfTransferAmountIsZero(amount);
    }

    public void setUpTransactionRouts(TransactionServiceModel transactionServiceModel) {
        setSender(transactionServiceModel);
        setRecipient(transactionServiceModel);
        setCurrency(transactionServiceModel);
        setOrder(transactionServiceModel);
    }

    public void setOrder(TransactionServiceModel transactionServiceModel) {
        orderRepository.findById(transactionServiceModel.getOrder().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setOrder(this.modelMapper.map(getOrderForTransaction(transactionServiceModel), OrderServiceModel.class));
                });
    }

    public void setCurrency(TransactionServiceModel transactionServiceModel) {
        currencyRepository.findById(transactionServiceModel.getCurrency().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setCurrency(this.modelMapper.map(getCurrencyForTransaction(transactionServiceModel), CurrencyServiceModel.class));
                });
    }

    public void setRecipient(TransactionServiceModel transactionServiceModel) {
        cardRepository.findById(transactionServiceModel.getRecipient().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setRecipient(mapCardServiceViewModelToCardServiceModel(getRecipientCard(transactionServiceModel)));
                });
    }

    public void setSender(TransactionServiceModel transactionServiceModel) {
        cardRepository.findById(transactionServiceModel.getSender().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setSender(mapCardServiceViewModelToCardServiceModel(getSenderCard(transactionServiceModel)));
                });
    }

    private OrderServiceViewModel getOrderForTransaction(TransactionServiceModel transactionServiceModel) {
        return this.orderService.getOrderById(transactionServiceModel.getOrder().getId());
    }

    private CurrencyServiceViewModel getCurrencyForTransaction(TransactionServiceModel transactionServiceModel) {
        return this.currencyService.getCurrencyById(transactionServiceModel.getCurrency().getId());
    }

    private CardServiceViewModel getRecipientCard(TransactionServiceModel transactionServiceModel) {
        return this.cardService.getCardById(transactionServiceModel.getRecipient().getId());
    }



    private void cardServiceUpdate(Card card) {
        cardService.updateCard(mapCardToCardServiceModel(card));
    }

    private Transaction getTransactionId(Long id) {
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Transaction with id '%d' not found .", id)));
    }

    private Card getCardId(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Card with id '%d' not found .", id)));
    }

    private void checksIfDepositAmountIsZero(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Deposit amount cannot be : %.2f",
                            amount));
        }
    }

    private void checksIfTransferAmountIsZero(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Transfer amount cannot be : %.2f",
                            amount));
        }
    }

    private CardServiceModel mapCardServiceViewModelToCardServiceModel(CardServiceViewModel senderCard) {
        return this.modelMapper.map(senderCard, CardServiceModel.class);
    }

    private CardServiceViewModel getSenderCard(TransactionServiceModel transactionServiceModel) {
        return this.cardService.getCardById(transactionServiceModel.getSender().getId());
    }


    public Transaction mapTransactionServiceModelToTransaction(TransactionServiceModel transactionServiceModel) {
        return this.modelMapper.map(transactionServiceModel, Transaction.class);
    }

    public CardServiceModel mapCardToCardServiceModel(Card card) {
        return this.modelMapper.map(card, CardServiceModel.class);
    }

    public TransactionServiceViewModel mapTransactionToTransactionServiceViewModel(Transaction transaction) {
        return this.modelMapper.map(transaction, TransactionServiceViewModel.class);
    }

    public TransactionServiceModel mapTransactionToTransactionServiceModel(Transaction transaction) {
        return this.modelMapper.map(transaction, TransactionServiceModel.class);
    }

}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CardDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CurrencyDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OrderDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.TransactionDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.TransactionStatus;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.*;
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

/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final CardDao cardDao;
    private final CardService cardService;
    private final CurrencyService currencyService;
    private final CurrencyDao currencyDao;
    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final OrderDao orderDao;

    /**
     * Constructor
     */
    public TransactionServiceImpl(TransactionDao transactionDao, CardDao cardDao,
                                  CardService cardService, CurrencyService currencyService, CurrencyDao currencyDao,
                                  ModelMapper modelMapper, OrderService orderService, OrderDao orderDao) {
        this.transactionDao = transactionDao;
        this.cardDao = cardDao;
        this.cardService = cardService;
        this.currencyService = currencyService;
        this.currencyDao = currencyDao;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.orderDao = orderDao;
    }

    /**
     * @param transactionServiceModel
     * @return
     */
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
        return mapTransactionToTransactionServiceViewModel(this.transactionDao.saveAndFlush(transaction));
    }

    /**
     * @param transactionServiceModel
     * @return
     */
    @Override
    @Transactional
    public TransactionServiceViewModel updateTransaction(TransactionServiceModel transactionServiceModel) {
        Transaction transaction = mapTransactionServiceModelToTransaction(transactionServiceModel);
        getTransactionId(transactionServiceModel.getId());
        return mapTransactionToTransactionServiceViewModel(this.transactionDao.saveAndFlush(transaction));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TransactionServiceViewModel getTransactionById(Long id) {
        return mapTransactionToTransactionServiceViewModel(this.getTransactionId(id));
    }

    /**
     * @return
     */
    @Override
    public List<TransactionServiceViewModel> getAllTransactions() {
        List<Transaction> transactions = transactionDao.findAll();
        return modelMapper.map(transactions, new TypeToken<List<TransactionServiceViewModel>>() {
        }.getType());
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public TransactionServiceViewModel deleteTransactionById(Long id) {
        Transaction transaction = getTransactionId(id);
        this.transactionDao.deleteById(id);
        return mapTransactionToTransactionServiceViewModel(transaction);
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public TransactionServiceViewModel refundTransactionById(Long id) {
        Transaction transaction = getTransactionId(id);
        this.refund(transaction.getRecipient().getId(), transaction.getSender().getId(), transaction.getAmount());
        this.updateTransaction(mapTransactionToTransactionServiceModel(transaction));
        return mapTransactionToTransactionServiceViewModel(transaction);
    }


    /**
     * @param id
     * @param amount
     */
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
        // cardServiceUpdate(card);
    }


    /**
     * @param id
     * @param amount
     */
    @Override
    @Transactional
    public void depositMoney(Long id, BigDecimal amount) {
        Card card = getCardId(id);
        checksIfDepositAmountIsZero(amount);
        card.setBalance(card.getBalance().add(amount));
    }


    /**
     * @param toId
     * @param fromId
     * @param amount
     */
    @Override
    @Transactional
    public void transferMoney(Long toId, Long fromId, BigDecimal amount) {
        depositMoney(toId, amount);
        withdrawMoney(fromId, amount);
        checksIfTransferAmountIsZero(amount);
    }

    /**
     * @param toId
     * @param fromId
     * @param amount
     */
    @Override
    @Transactional
    public void refund(Long toId, Long fromId, BigDecimal amount) {
        depositMoney(fromId, amount);
        withdrawMoney(toId, amount);
        checksIfTransferAmountIsZero(amount);
    }

    /**
     * @param transactionServiceModel
     */
    public void setUpTransactionRouts(TransactionServiceModel transactionServiceModel) {
        setSender(transactionServiceModel);
        setRecipient(transactionServiceModel);
        setCurrency(transactionServiceModel);
        setOrder(transactionServiceModel);
    }

    /**
     * @param transactionServiceModel
     */
    public void setOrder(TransactionServiceModel transactionServiceModel) {
        orderDao.findById(transactionServiceModel.getOrder().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setOrder(this.modelMapper.map(getOrderForTransaction(transactionServiceModel), OrderServiceModel.class));
                });
    }

    /**
     * @param transactionServiceModel
     */
    public void setCurrency(TransactionServiceModel transactionServiceModel) {
        currencyDao.findById(transactionServiceModel.getCurrency().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setCurrency(this.modelMapper.map(getCurrencyForTransaction(transactionServiceModel), CurrencyServiceModel.class));
                });
    }

    /**
     * @param transactionServiceModel
     */
    public void setRecipient(TransactionServiceModel transactionServiceModel) {
        cardDao.findById(transactionServiceModel.getRecipient().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setRecipient(mapCardServiceViewModelToCardServiceModel(getRecipientCard(transactionServiceModel)));
                });
    }

    /**
     * @param transactionServiceModel
     */
    public void setSender(TransactionServiceModel transactionServiceModel) {
        cardDao.findById(transactionServiceModel.getSender().getId())
                .ifPresent(c -> {
                    transactionServiceModel.setSender(mapCardServiceViewModelToCardServiceModel(getSenderCard(transactionServiceModel)));
                });
    }

    /**
     * @param transactionServiceModel
     * @return
     */
    private OrderServiceViewModel getOrderForTransaction(TransactionServiceModel transactionServiceModel) {
        return this.orderService.getOrderById(transactionServiceModel.getOrder().getId());
    }

    /**
     * @param transactionServiceModel
     * @return
     */
    private CurrencyServiceViewModel getCurrencyForTransaction(TransactionServiceModel transactionServiceModel) {
        return this.currencyService.getCurrencyById(transactionServiceModel.getCurrency().getId());
    }

    /**
     * @param transactionServiceModel
     * @return
     */
    private CardServiceViewModel getRecipientCard(TransactionServiceModel transactionServiceModel) {
        return this.cardService.getCardById(transactionServiceModel.getRecipient().getId());
    }


    /**
     * @param id
     * @return
     */
    private Transaction getTransactionId(Long id) {
        return this.transactionDao.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Transaction with id '%d' not found .", id)));
    }

    /**
     * @param id
     * @return
     */
    private Card getCardId(Long id) {
        return cardDao.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Card with id '%d' not found .", id)));
    }

    /**
     * @param amount
     */
    private void checksIfDepositAmountIsZero(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Deposit amount cannot be : %.2f",
                            amount));
        }
    }

    /**
     * @param amount
     */
    private void checksIfTransferAmountIsZero(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalCardTransactionOperation(String.
                    format("Transfer amount cannot be : %.2f",
                            amount));
        }
    }

    /**
     * @param senderCard
     * @return
     */
    private CardServiceModel mapCardServiceViewModelToCardServiceModel(CardServiceViewModel senderCard) {
        return this.modelMapper.map(senderCard, CardServiceModel.class);
    }

    /**
     * @param transactionServiceModel
     * @return
     */
    private CardServiceViewModel getSenderCard(TransactionServiceModel transactionServiceModel) {
        return this.cardService.getCardById(transactionServiceModel.getSender().getId());
    }


    /**
     * @param transactionServiceModel
     * @return
     */
    public Transaction mapTransactionServiceModelToTransaction(TransactionServiceModel transactionServiceModel) {
        return this.modelMapper.map(transactionServiceModel, Transaction.class);
    }


    /**
     * @param transaction
     * @return
     */
    public TransactionServiceViewModel mapTransactionToTransactionServiceViewModel(Transaction transaction) {
        return this.modelMapper.map(transaction, TransactionServiceViewModel.class);
    }

    /**
     * @param transaction
     * @return
     */
    public TransactionServiceModel mapTransactionToTransactionServiceModel(Transaction transaction) {
        return this.modelMapper.map(transaction, TransactionServiceModel.class);
    }

}

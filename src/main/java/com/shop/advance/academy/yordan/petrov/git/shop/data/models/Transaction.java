package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.TransactionStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    private String number;
    private Card recipient;
    private Card sender;
    private String description;
    private Instant dateCreated;
    private Instant dateCompleted;
    private Instant dateUpdated;
    private TransactionStatus transactionStatus = TransactionStatus.NEW;
    private BigDecimal fee;
    private BigDecimal amount;
    private Currency currency;
    private Order order;

    /**
     * Constructor
     */
    public Transaction() {
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    /**
     * Method for
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToOne(targetEntity = Card.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    public Card getRecipient() {
        return this.recipient;
    }

    /**
     * Method for
     *
     * @param recipient
     */
    public void setRecipient(Card recipient) {
        this.recipient = recipient;
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToOne(targetEntity = Card.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    public Card getSender() {
        return this.sender;
    }

    /**
     * Method for
     *
     * @param sender
     */
    public void setSender(Card sender) {
        this.sender = sender;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    /**
     * Method for
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Instant getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "date_completed")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Instant getDateCompleted() {
        return this.dateCompleted;
    }

    /**
     * Method for
     *
     * @param dateCompleted
     */
    public void setDateCompleted(Instant dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Instant getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Method for
     *
     * @param dateUpdated
     */
    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    /**
     * Method for
     *
     * @param transactionStatus
     */
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "fee")
    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Method for
     *
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToOne(targetEntity = Currency.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "currency", referencedColumnName = "id")
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * Method for
     *
     * @param currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToOne(targetEntity = Order.class,
            cascade = {CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order getOrder() {
        return this.order;
    }

    /**
     * Method for
     *
     * @param order
     */
    public void setOrder(Order order) {
        this.order = order;
    }
}

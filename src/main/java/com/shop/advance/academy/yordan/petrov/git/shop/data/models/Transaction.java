package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.TransactionStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

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

    public Transaction() {
    }

    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne(targetEntity = Card.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    public Card getRecipient() {
        return this.recipient;
    }

    public void setRecipient(Card recipient) {
        this.recipient = recipient;
    }

    @ManyToOne(targetEntity = Card.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    public Card getSender() {
        return this.sender;
    }

    public void setSender(Card sender) {
        this.sender = sender;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Instant getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "date_completed")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Instant getDateCompleted() {
        return this.dateCompleted;
    }

    public void setDateCompleted(Instant dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Column(name = "date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Instant getDateUpdated() {
        return this.dateUpdated;
    }

    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Column(name = "fee")
    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Column(name = "amount")
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToOne(targetEntity = Currency.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "currency", referencedColumnName = "id")
    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @ManyToOne(targetEntity = Order.class,
            cascade = {CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

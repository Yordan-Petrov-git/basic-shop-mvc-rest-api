package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class TransactionServiceModel {

    Long id;
    private String number;
    private CardServiceModel recipient;
    private CardServiceModel sender;
    private String description;
    private Instant dateCreated;
    private Instant dateCompleted;
    private Instant dateUpdated;
    private TransactionStatus transactionStatus = TransactionStatus.NEW;
    private BigDecimal fee;
    private BigDecimal amount;
    private CurrencyServiceModel currency;
    private OrderServiceModel order;

    /**
     * Constructor
     */
    public TransactionServiceModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CardServiceModel getRecipient() {
        return this.recipient;
    }

    public void setRecipient(CardServiceModel recipient) {
        this.recipient = recipient;
    }

    public CardServiceModel getSender() {
        return this.sender;
    }

    public void setSender(CardServiceModel sender) {
        this.sender = sender;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateCompleted() {
        return this.dateCompleted;
    }

    public void setDateCompleted(Instant dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Instant getDateUpdated() {
        return this.dateUpdated;
    }

    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyServiceModel getCurrency() {
        return this.currency;
    }

    public void setCurrency(CurrencyServiceModel currency) {
        this.currency = currency;
    }

    public OrderServiceModel getOrder() {
        return this.order;
    }

    public void setOrder(OrderServiceModel order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionServiceModel)) return false;
        TransactionServiceModel that = (TransactionServiceModel) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(dateCompleted, that.dateCompleted) &&
                Objects.equals(dateUpdated, that.dateUpdated) &&
                transactionStatus == that.transactionStatus &&
                Objects.equals(fee, that.fee) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, description, dateCreated, dateCompleted, dateUpdated, transactionStatus, fee, amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionServiceModel{");
        sb.append("number='").append(number).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateCompleted=").append(dateCompleted);
        sb.append(", dateUpdated=").append(dateUpdated);
        sb.append(", transactionStatus=").append(transactionStatus);
        sb.append(", fee=").append(fee);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}

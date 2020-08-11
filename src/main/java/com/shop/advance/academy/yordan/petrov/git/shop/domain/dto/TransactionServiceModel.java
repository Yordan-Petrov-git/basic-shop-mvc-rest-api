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

    /**
     * The Id.
     */
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


    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets recipient.
     *
     * @return the recipient
     */
    public CardServiceModel getRecipient() {
        return this.recipient;
    }

    /**
     * Sets recipient.
     *
     * @param recipient the recipient
     */
    public void setRecipient(CardServiceModel recipient) {
        this.recipient = recipient;
    }

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public CardServiceModel getSender() {
        return this.sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(CardServiceModel sender) {
        this.sender = sender;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public Instant getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets date completed.
     *
     * @return the date completed
     */
    public Instant getDateCompleted() {
        return this.dateCompleted;
    }

    /**
     * Sets date completed.
     *
     * @param dateCompleted the date completed
     */
    public void setDateCompleted(Instant dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * Gets date updated.
     *
     * @return the date updated
     */
    public Instant getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Sets date updated.
     *
     * @param dateUpdated the date updated
     */
    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * Gets transaction status.
     *
     * @return the transaction status
     */
    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    /**
     * Sets transaction status.
     *
     * @param transactionStatus the transaction status
     */
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     * Gets fee.
     *
     * @return the fee
     */
    public BigDecimal getFee() {
        return this.fee;
    }

    /**
     * Sets fee.
     *
     * @param fee the fee
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public CurrencyServiceModel getCurrency() {
        return this.currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(CurrencyServiceModel currency) {
        this.currency = currency;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public OrderServiceModel getOrder() {
        return this.order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
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

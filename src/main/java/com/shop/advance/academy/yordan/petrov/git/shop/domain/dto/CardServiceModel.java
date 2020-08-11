package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.CardProviders;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.CardType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class CardServiceModel {

    private Long id;
    private CardType cardType = CardType.NONE;
    private CardProviders cardProviders = CardProviders.NONE;
    private LocalDate expirationDate;
    private LocalDateTime dateIssued;
    private String cvvCode;
    private String pinCode;
    private String number;
    private BigDecimal balance;
    private CurrencyServiceModel currency;
    private boolean isActive = true;
    private List<UserServiceModel> users = new ArrayList<>();

    /**
     * Constructor
     */
    public CardServiceModel() {
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
     * Gets card type.
     *
     * @return the card type
     */
    public CardType getCardType() {
        return this.cardType;
    }

    /**
     * Sets card type.
     *
     * @param cardType the card type
     */
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * Gets card providers.
     *
     * @return the card providers
     */
    public CardProviders getCardProviders() {
        return this.cardProviders;
    }

    /**
     * Sets card providers.
     *
     * @param cardProviders the card providers
     */
    public void setCardProviders(CardProviders cardProviders) {
        this.cardProviders = cardProviders;
    }

    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets date issued.
     *
     * @return the date issued
     */
    public LocalDateTime getDateIssued() {
        return this.dateIssued;
    }

    /**
     * Sets date issued.
     *
     * @param dateIssued the date issued
     */
    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

    /**
     * Gets cvv code.
     *
     * @return the cvv code
     */
    public String getCvvCode() {
        return this.cvvCode;
    }

    /**
     * Sets cvv code.
     *
     * @param cvvCode the cvv code
     */
    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    /**
     * Gets pin code.
     *
     * @return the pin code
     */
    public String getPinCode() {
        return this.pinCode;
    }

    /**
     * Sets pin code.
     *
     * @param pinCode the pin code
     */
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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
     * Gets balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return this.balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<UserServiceModel> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(List<UserServiceModel> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardServiceModel)) return false;
        CardServiceModel that = (CardServiceModel) o;
        return isActive == that.isActive &&
                Objects.equals(id, that.id) &&
                cardType == that.cardType &&
                cardProviders == that.cardProviders &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(cvvCode, that.cvvCode) &&
                Objects.equals(pinCode, that.pinCode) &&
                Objects.equals(number, that.number) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardType, cardProviders, expirationDate, cvvCode, pinCode, number, balance, isActive);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardServiceModel{");
        sb.append("id=").append(id);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardProviders=").append(cardProviders);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", cvvCode='").append(cvvCode).append('\'');
        sb.append(", pinCode='").append(pinCode).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}

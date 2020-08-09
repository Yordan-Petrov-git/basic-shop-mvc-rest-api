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
public class CardServiceViewModel {

    private Long id;
    private CardType cardType = CardType.NONE;
    private CardProviders cardProviders = CardProviders.NONE;
    private LocalDate expirationDate;
    private LocalDateTime dateIssued;
    private String cvvCode;
    private String number;
    private BigDecimal balance;
    private CurrencyServiceViewModel currency;
    private boolean isActive = true;
    private List<UserServiceModel> users = new ArrayList<>();

    /**
     * Constructor
     */
    public CardServiceViewModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardProviders getCardProviders() {
        return this.cardProviders;
    }

    public void setCardProviders(CardProviders cardProviders) {
        this.cardProviders = cardProviders;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }


    public LocalDateTime getDateIssued() {
        return this.dateIssued;
    }

    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getCvvCode() {
        return this.cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }


    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CurrencyServiceViewModel getCurrency() {
        return this.currency;
    }

    public void setCurrency(CurrencyServiceViewModel currency) {
        this.currency = currency;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<UserServiceModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserServiceModel> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardServiceViewModel)) return false;
        CardServiceViewModel that = (CardServiceViewModel) o;
        return isActive == that.isActive &&
                Objects.equals(id, that.id) &&
                cardType == that.cardType &&
                cardProviders == that.cardProviders &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(cvvCode, that.cvvCode) &&
                Objects.equals(number, that.number) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardType, cardProviders, expirationDate, cvvCode, number, balance, isActive);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardServiceViewModel{");
        sb.append("id=").append(id);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardProviders=").append(cardProviders);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", cvvCode='").append(cvvCode).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.CardProviders;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.CardType;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    private CardType cardType = CardType.NONE;
    private CardProviders cardProviders = CardProviders.NONE;
    private LocalDate expirationDate;
    private LocalDateTime dateIssued;
    private String cvvCode;
    private String pinCode;
    private String number;
    private BigDecimal balance;
    private Currency currency;
    private boolean isActive = true;


    public Card() {
    }

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    public CardType getCardType() {
        return this.cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Column(name = "card_provider")
    @Enumerated(EnumType.STRING)
    public CardProviders getCardProviders() {
        return this.cardProviders;
    }

    public void setCardProviders(CardProviders cardProviders) {
        this.cardProviders = cardProviders;
    }

    @Column(name = "expiration_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Column(name = "issued_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDateIssued() {
        return this.dateIssued;
    }

    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

    @Pattern(regexp = "^\\d{3}$", message = "cvv code is only 3 numbers")
    @Column(name = "cvv_code")
    public String getCvvCode() {
        return this.cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    //TODO ADD PIN VALIDATION SOMEHOW
    @Column(name = "pin")
    //@Pattern(regexp = "^\\d{4}$|^\\d{8}$",message = "pin number must be either 4 or 8 numbers long")
    public String getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "balance")
    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    @ManyToOne(targetEntity = Currency.class
            , fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH})
    @JoinColumn(name = "currency_id"
            , referencedColumnName = "id")
    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "is_active")
    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        if (!super.equals(o)) return false;
        Card card = (Card) o;
        return isActive == card.isActive &&
                cardType == card.cardType &&
                cardProviders == card.cardProviders &&
                Objects.equals(expirationDate, card.expirationDate) &&
                Objects.equals(cvvCode, card.cvvCode) &&
                Objects.equals(pinCode, card.pinCode) &&
                Objects.equals(number, card.number) &&
                Objects.equals(balance, card.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardType, cardProviders, expirationDate, cvvCode, pinCode, number, balance, isActive);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("cardType=").append(cardType);
        sb.append(", cardProviders=").append(cardProviders);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", cvvCode='").append(cvvCode).append('\'');
        sb.append(", pinCode='").append(pinCode).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", isActive=").append(isActive);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.TransactionStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
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
    private final TransactionStatus transactionStatus = TransactionStatus.NEW;
    private Long fee;
    private BigDecimal amount;
    private Currency currency;

}

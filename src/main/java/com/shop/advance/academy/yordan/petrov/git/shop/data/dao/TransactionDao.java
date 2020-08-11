package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

/**
 * Interface dao for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Transaction> findById(Long id);

    /**
     * @param number
     * @return
     */
    Optional<Transaction> findByNumber(String number);

    /**
     * @param card
     * @return
     */
    Optional<Transaction> findByRecipient(Card card);

    /**
     * @param card
     * @return
     */
    Optional<Transaction> findBySender(Card card);

    /**
     * @param card
     * @return
     */
    Optional<Transaction> findAllByRecipient(Card card);

    /**
     * @param card
     * @return
     */
    Optional<Transaction> findAllBySender(Card card);

    /**
     * @param dateCreated
     * @return
     */
    Optional<Transaction> findAllByDateCreated(Instant dateCreated);

    /**
     * @param dateComplected
     * @return
     */
    Optional<Transaction> findAllByDateCompleted(Instant dateComplected);

    /**
     * @param dateUpdated
     * @return
     */
    Optional<Transaction> findAllByDateUpdated(Instant dateUpdated);

    /**
     * @param status
     * @return
     */
    Optional<Transaction> findAllByTransactionStatus(TransactionStatus status);

}

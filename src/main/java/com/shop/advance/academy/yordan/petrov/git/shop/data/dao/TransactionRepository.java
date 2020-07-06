package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Transaction;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

 //   Optional<Transaction> findById(Long id);

  //  Optional<Transaction> findByNumber(String number);

//    Optional<Transaction> findByRecipient_Id(Long id);

//    Optional<Transaction> findBySender(Card card);
//
//    Optional<Transaction> findByDateCreated(Instant dateCreated);
//
//    Optional<Transaction> findByDateCompleted(Instant dateComplected);
//
//    Optional<Transaction> findByDateUpdated(Instant dateUpdated);
//
//    Optional<Transaction> findByTransactionStatus(TransactionStatus status);





}

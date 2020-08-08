package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Message;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageDao extends JpaRepository<Message, Long> {

    Optional<Message> findBySenderId(User id);

    Optional<Message> findByReciverId(User id);


}

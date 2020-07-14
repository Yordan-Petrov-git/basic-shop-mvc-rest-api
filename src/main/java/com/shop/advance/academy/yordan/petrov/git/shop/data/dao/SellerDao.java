package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Seller;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerDao extends JpaRepository<Seller, Long> {

    Optional<Seller> findById(Long id);

    Optional<Seller> findByName(String name);

    Optional<Seller> findByUser(User user);

}




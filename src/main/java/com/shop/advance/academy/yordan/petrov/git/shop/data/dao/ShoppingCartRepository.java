package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findById(Long id);

    Optional<ShoppingCart> findByAddedItems (Item item);

}


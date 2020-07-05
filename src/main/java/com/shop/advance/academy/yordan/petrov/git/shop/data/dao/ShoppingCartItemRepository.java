package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Long> {

    Optional<ShoppingCartItem> findById(Long id);

}

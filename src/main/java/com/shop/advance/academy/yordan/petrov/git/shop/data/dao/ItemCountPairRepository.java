package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ItemCountPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCountPairRepository extends JpaRepository<ItemCountPair, Long> {

    Optional<ItemCountPair> findById(Long id);

}

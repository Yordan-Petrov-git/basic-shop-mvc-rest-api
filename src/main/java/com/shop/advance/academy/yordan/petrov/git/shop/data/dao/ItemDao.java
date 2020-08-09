package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
/**
 * Interface dao for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Repository
public interface ItemDao extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);

    Optional<Item> findByTitleAndDescription(String title, String description);

    Optional<Item> findByTitle(String title);

    List<Item> findByTitleLike(String title);

    Optional<Item> findAllByDescriptionContaining(String description);

    Optional<Item> findAllByPrice(BigDecimal price);

    Optional<Item> findAllByWeight(Double weight);

    Optional<Item> findAllByItemCategory(ItemCategory itemCategory);

}


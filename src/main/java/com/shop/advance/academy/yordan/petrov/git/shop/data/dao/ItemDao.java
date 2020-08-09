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

    /**
     * @param id
     * @return
     */
    Optional<Item> findById(Long id);

    /**
     * @param title
     * @param description
     * @return
     */
    Optional<Item> findByTitleAndDescription(String title, String description);

    /**
     * @param title
     * @return
     */
    Optional<Item> findByTitle(String title);

    /**
     * @param title
     * @return
     */
    List<Item> findByTitleLike(String title);

    /**
     * @param description
     * @return
     */
    Optional<Item> findAllByDescriptionContaining(String description);

    /**
     * @param price
     * @return
     */
    Optional<Item> findAllByPrice(BigDecimal price);

    /**
     * @param weight
     * @return
     */
    Optional<Item> findAllByWeight(Double weight);

    /**
     * @param itemCategory
     * @return
     */
    Optional<Item> findAllByItemCategory(ItemCategory itemCategory);

}


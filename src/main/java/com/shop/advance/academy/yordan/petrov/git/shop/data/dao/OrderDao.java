package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Interface dao for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Order> findById(Long id);

    /**
     * @param orderNumber
     * @return
     */
    Optional<Order> findByNumber(String orderNumber);

}


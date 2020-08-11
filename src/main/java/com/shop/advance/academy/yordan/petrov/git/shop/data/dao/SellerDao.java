package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Seller;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;
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
public interface SellerDao extends JpaRepository<Seller, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Seller> findById(Long id);

    /**
     * @param name
     * @return
     */
    Optional<Seller> findByName(String name);

    /**
     * @param user
     * @return
     */
    Optional<Seller> findByUser(User user);

}




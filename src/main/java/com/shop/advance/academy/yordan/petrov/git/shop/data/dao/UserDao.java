package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * @param id
     * @return
     */
    Optional<User> findById(Long id);

    /**
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * @param username
     * @return
     */
    List<User> findByUsernameLike(String username);

    /**
     * @param firstName
     * @return
     */
    Optional<User> findByFirstName(String firstName);

    /**
     * @param firstName
     * @return
     */
    List<User> findByFirstNameLike(String firstName);

    /**
     * @param lastName
     * @return
     */
    Optional<User> findByLastName(String lastName);

    /**
     * @param lastName
     * @return
     */
    List<User> findByLastNameLike(String lastName);

    /**
     * @param firstName
     * @param lastName
     * @return
     */
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * @param firstName
     * @param lastName
     * @return
     */
    List<User> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);

}

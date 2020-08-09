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

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findByUsernameLike(String username);

    Optional<User> findByFirstName(String firstName);

    List<User> findByFirstNameLike(String firstName);

    Optional<User> findByLastName(String lastName);

    List<User> findByLastNameLike(String lastName);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);

}

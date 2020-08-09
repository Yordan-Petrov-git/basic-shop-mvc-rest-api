package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interface service for searching up the user .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface UserSearchService {

    /**
     * @param username
     * @return
     */
    UserServiceViewModel getUserByUsername(String username);

    /**
     * @param username
     * @return
     */
    List<UserServiceViewModel> getUserByUsernameLike(String username);

    /**
     * @param firstName
     * @return
     */
    UserServiceViewModel getUserByFirstName(String firstName);

    /**
     * @param firstName
     * @return
     */
    List<UserServiceViewModel> getUserByFirstNameLike(String firstName);

    /**
     * @param lastName
     * @return
     */
    UserServiceViewModel getUserByLastName(String lastName);

    /**
     * @param lastName
     * @return
     */
    List<UserServiceViewModel> getUserByLastNameLike(String lastName);

    /**
     * @param firstName
     * @param lastName
     * @return
     */
    UserServiceViewModel getUserByFirstNameAndLastName(String firstName, String lastName);

    /**
     * @param firstName
     * @param lastName
     * @return
     */
    List<UserServiceViewModel> getUserByFirstNameLikeAndLastNameLike(String firstName, String lastName);

}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface service for the user crud operations.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface UserService extends UserDetailsService, UserSearchService {

    /**
     * Method for
     * Method for
     *
     * @param userServiceModel
     * @return
     */
    UserServiceViewModel createUser(UserServiceModel userServiceModel);

    /**
     * Method for
     *
     * @param userServiceModel
     * @return
     */
    UserServiceViewModel updateUser(UserServiceModel userServiceModel);

    /**
     * Method for
     *
     * @param id
     * @return
     */
    UserServiceViewModel getUserById(long id);

    /**
     * Method for
     *
     * @return
     */
    List<UserServiceViewModel> getAllUsers();

    /**
     * Method for
     *
     * @param id
     * @return
     */
    UserServiceViewModel deleteUserById(long id);
}

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

    UserServiceViewModel createUser(UserServiceModel userServiceModel);

    UserServiceViewModel updateUser(UserServiceModel userServiceModel);

    UserServiceViewModel getUserById(long id);

    List<UserServiceViewModel> getAllUsers();

    UserServiceViewModel deleteUserById(long id);
}

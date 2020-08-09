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

    UserServiceViewModel getUserByUsername(String username);

    List<UserServiceViewModel> getUserByUsernameLike(String username);

    UserServiceViewModel getUserByFirstName(String firstName);

    List<UserServiceViewModel> getUserByFirstNameLike(String firstName);

    UserServiceViewModel getUserByLastName(String lastName);

    List<UserServiceViewModel> getUserByLastNameLike(String lastName);

    UserServiceViewModel getUserByFirstNameAndLastName(String firstName, String lastName);

    List<UserServiceViewModel> getUserByFirstNameLikeAndLastNameLike(String firstName, String lastName);

}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

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

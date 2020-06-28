package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService extends UserDetailsService {


    UserServiceViewModel createUser(UserServiceModel user);

    UserServiceViewModel updateUser(UserServiceModel user);

    UserServiceViewModel getUserById(long id);

    List<UserServiceViewModel> getAllUsers();

    UserServiceViewModel deleteUserById(long id);

}

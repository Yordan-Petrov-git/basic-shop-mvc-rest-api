package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserRepositoryReturnsAllUsers() {
        List<User> usersToAdd = new ArrayList<>();
        usersToAdd.add(new User());

        Mockito.when(userRepository.findAll()).thenReturn(usersToAdd);
        List<UserServiceViewModel> usersFetchedFromRepo = userService.getAllUsers();

        assertEquals(1, usersFetchedFromRepo.size());
    }

    @Test
    public void testUserServiceCreateExistingUserThrowsException() {
        User user = new User();
        user.setUsername("UserNameOfTheUserToTestIfItThrowsException");

        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));
        UserServiceModel userServiceModel = this.modelMapper.map(user,UserServiceModel.class);

        assertThrows(InvalidEntityException.class, () -> userService.createUser(userServiceModel));
    }

    @Test
    public void testUserServiceGetUserById() {
        User user = new User();
        user.setId(5L);

        Mockito.when(userRepository.findById(5L))
                .thenReturn(java.util.Optional.of(user));
        UserServiceViewModel userServiceModel = this.modelMapper.map(user,UserServiceViewModel.class);

        assertEquals(userServiceModel, userService.getUserById(5L));
    }

    @Test
    public void testUserServiceFromSpringSecurityLoadUserByUsername() {
        User user = new User();
        user.setUsername("UsernameForTesting");

        Mockito.when(userRepository.findByUsername("UsernameForTesting"))
                .thenReturn(java.util.Optional.of(user));

        assertEquals(user, userService.loadUserByUsername("UsernameForTesting"));
    }

    @Test
    public void testUserServiceFromSpringSecurityLoadUserByUsernameThrowsException() {
        User user = new User();
        user.setUsername("UsernameForTesting");

        Mockito.when(userRepository.findByUsername("UsernameForTesting"))
                .thenReturn(java.util.Optional.of(user));

        assertThrows(InvalidEntityException.class, () -> userService.loadUserByUsername("Username"));
    }

}
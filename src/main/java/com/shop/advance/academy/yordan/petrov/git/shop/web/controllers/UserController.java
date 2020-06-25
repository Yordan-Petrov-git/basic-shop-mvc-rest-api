package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<UserServiceModel> createUser(@RequestBody UserServiceModel userServiceModel) {
        userService.createUser(userServiceModel);
        return new ResponseEntity<>(userServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id,@RequestBody UserServiceModel userServiceModel) {
        userService.updateUser(userServiceModel);
    }


    @GetMapping("/{id}")
    public UserServiceViewModel getUser(@PathVariable("id")final Long id) {
        return userService.getUserById(id);
    }

    @GetMapping()
    public List<UserServiceViewModel> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

}

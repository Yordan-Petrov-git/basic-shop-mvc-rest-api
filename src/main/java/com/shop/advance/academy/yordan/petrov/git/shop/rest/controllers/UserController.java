package com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("api/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<UserServiceViewModel> createUser(@RequestBody UserServiceModel userServiceModel) {

        UserServiceViewModel userServiceViewModel = userService.createUser(userServiceModel);

        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "createUser", UserServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(userServiceViewModel.getId()).toUri();

        log.info("User created: {}", location);

        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceViewModel);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserServiceViewModel> updateUser(@PathVariable("id") Long id, @RequestBody UserServiceModel userServiceModel) {

        UserServiceViewModel userServiceViewModel = userService.updateUser(userServiceModel);

 
        log.info("User updated: {}", userServiceViewModel);


        return ResponseEntity.status(HttpStatus.OK).body(userServiceViewModel);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserServiceViewModel> partialUpdateUser(@PathVariable("id") Long id, @RequestBody UserServiceModel userServiceModel) {

        UserServiceViewModel userServiceViewModel = userService.updateUser(userServiceModel);



        log.info("User updated: {} , ",  userServiceViewModel);


        return ResponseEntity.status(HttpStatus.OK).body(userServiceViewModel);
    }


    @GetMapping("{id}")
    public ResponseEntity<UserServiceViewModel> getUser(@PathVariable("id") final Long id) {

        UserServiceViewModel userServiceViewModel = userService.getUserById(id);





        return ResponseEntity.status(HttpStatus.FOUND).body(userServiceViewModel);

    }

    @GetMapping()
    public ResponseEntity<List<UserServiceViewModel>> getUsers() {

        List<UserServiceViewModel> userServiceViewModel = userService.getAllUsers();

        log.info("Users Found: {} ", userServiceViewModel);

        return ResponseEntity.status(HttpStatus.FOUND).body(userServiceViewModel);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserServiceViewModel> deleteUser(@PathVariable("id") Long id) {

        UserServiceViewModel userServiceViewModel = userService.deleteUserById(id);

        log.info("Users deleted: {} ", userServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(userServiceViewModel);

    }


}

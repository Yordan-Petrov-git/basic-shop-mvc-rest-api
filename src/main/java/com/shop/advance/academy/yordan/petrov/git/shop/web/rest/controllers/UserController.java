package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceViewModel;
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


/**
 *
 */
@RestController
@CrossOrigin
@RequestMapping("api/user")
@Slf4j
public class UserController {

    /**
     *
     */
    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param userServiceModel
     * @return
     */
    @PostMapping()
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<UserServiceViewModel> createUser(@RequestBody UserServiceModel userServiceModel) {
        UserServiceViewModel userServiceViewModel = userService.createUser(userServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "createUser", UserServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(userServiceViewModel.getId()).toUri();
        log.info("User created: {} {}", userServiceViewModel, location);
        return ResponseEntity.created(location).body(userServiceViewModel);
    }

    /**
     * @param userServiceModel
     * @return
     */
    @PutMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<UserServiceViewModel> updateUser(@RequestBody UserServiceModel userServiceModel) {
        UserServiceViewModel userServiceViewModel = userService.updateUser(userServiceModel);
        log.info("User updated: {}", userServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(userServiceViewModel);
    }

    /**
     * @param userServiceModel
     * @return
     */
    @PatchMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<UserServiceViewModel> partialUpdateUser(@RequestBody UserServiceModel userServiceModel) {
        UserServiceViewModel userServiceViewModel = userService.updateUser(userServiceModel);
        log.info("User updated: {} , ", userServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(userServiceViewModel);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<UserServiceViewModel> getUser(@PathVariable("id") final Long id) {
        UserServiceViewModel userServiceViewModel = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(userServiceViewModel);
    }

    /**
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserServiceViewModel>> getUsers() {
        List<UserServiceViewModel> userServiceViewModel = userService.getAllUsers();
        log.info("Users Found: {} ", userServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(userServiceViewModel);
    }

    /**
     * @param username
     * @return
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    @GetMapping("/serach/user/username/{username}")
    public ResponseEntity<UserServiceViewModel> getUserByUsername(@PathVariable String username) {
        UserServiceViewModel userServiceViewModel = userService.getUserByUsername(username);
        log.info("User  found : {}", userServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(userServiceViewModel);
    }

    /**
     * @param username
     * @return
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    @GetMapping("/serach/user/username/like/{username}")
    public ResponseEntity<List<UserServiceViewModel>> getUserByUsernameLike(@PathVariable String username) {
        List<UserServiceViewModel> userServiceViewModel = userService.getUserByUsernameLike(username);
        log.info("User  found : {}", userServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(userServiceViewModel);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserServiceViewModel> deleteUser(@PathVariable("id") Long id) {
        UserServiceViewModel userServiceViewModel = userService.deleteUserById(id);
        log.info("Users deleted: {} ", userServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(userServiceViewModel);
    }

}

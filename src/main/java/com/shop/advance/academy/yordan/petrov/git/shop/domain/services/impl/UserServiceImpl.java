package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.RoleRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Role;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.RoleService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserServiceModel createUser(@Valid UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel, User.class);

        this.userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new InvalidEntityException(String.format("User with username '%s' already exists.", user.getUsername()));
        });

        this.userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new InvalidEntityException(String.format("'%s' is  already registered.", user.getEmail()));
        });

        if (userRepository.count() == 0) {
            this.roleService.seedRolesInDatabase();

            user.setAuthorities(this.roleService.findAllRoles()
                    .stream()
                    .map(r -> this.modelMapper.map(r, Role.class))
                    .collect(Collectors.toSet()));

        } else {
            user.setAuthorities(new LinkedHashSet<>());
            user.getAuthorities()
                    .add(this.modelMapper.map(this.roleRepository.findByAuthority("USER"), Role.class));
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);

    }

    @Override
    public void updateUser(@Valid UserServiceModel user) {

    }

    @Override
    public UserServiceViewModel getUserById(long id) {

        return this.modelMapper
                .map(this.userRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("User with ID %s not found.", id))), UserServiceViewModel.class);
    }

    @Override
    public List<UserServiceViewModel> getAllUsers() {

        List<User> users = this.userRepository.findAll();

        return this.modelMapper.map(users, new TypeToken<List<UserServiceViewModel>>() {
        }.getType());

    }

    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElse(null);
    }
}

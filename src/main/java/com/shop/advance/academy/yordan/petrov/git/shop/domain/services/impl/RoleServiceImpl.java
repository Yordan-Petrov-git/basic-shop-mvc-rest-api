package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.RoleRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Role;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.RoleServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class    RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedRolesInDatabase() {

        Role admin = new Role("ROLE_ADMIN");
        Role moderator = new Role("ROLE_MODERATOR");
        Role user = new Role("ROLE_USER");

        this.roleRepository.saveAndFlush(admin);
        this.roleRepository.saveAndFlush(moderator);
        this.roleRepository.saveAndFlush(user);

    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {

        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());

    }

    @Override
    public RoleServiceModel findByAuthority(String role) {


        return this.modelMapper.map(this.roleRepository.findByAuthority(role), RoleServiceModel.class);
    }
}

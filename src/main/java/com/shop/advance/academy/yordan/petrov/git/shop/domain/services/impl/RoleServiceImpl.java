package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.RoleRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.RoleServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.RoleServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleServiceModel createRole(RoleServiceModel roleServiceModel) {
        return null;
    }

    @Override
    public void updateRole(RoleServiceModel roleServiceModel) {

    }

    @Override
    public RoleServiceViewModel getRoleById(long id) {
        return null;
    }

    @Override
    public List<RoleServiceViewModel> getAleRoles() {
        return null;
    }

    @Override
    public void deleteRoleById(long id) {

    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.RoleDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Role;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.RoleServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final ModelMapper modelMapper;

    /**
     * Constructor
     */
    @Autowired
    public RoleServiceImpl(RoleDao roleDao, ModelMapper modelMapper) {
        this.roleDao = roleDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDatabase() {
        Role admin = new Role("ROLE_ADMIN");
        Role moderator = new Role("ROLE_MODERATOR");
        Role user = new Role("ROLE_USER");
        this.roleDao.saveAndFlush(admin);
        this.roleDao.saveAndFlush(moderator);
        this.roleDao.saveAndFlush(user);
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return this.roleDao.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String role) {
        return this.modelMapper.map(this.roleDao.findByAuthority(role), RoleServiceModel.class);
    }
}

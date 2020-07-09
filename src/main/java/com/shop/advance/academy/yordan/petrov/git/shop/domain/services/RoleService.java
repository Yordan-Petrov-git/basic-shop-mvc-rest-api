package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.RoleServiceModel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {

    void seedRolesInDatabase();

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String role);
}

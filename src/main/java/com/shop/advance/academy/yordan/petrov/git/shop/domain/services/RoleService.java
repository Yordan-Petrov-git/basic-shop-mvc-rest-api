package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.RoleServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.RoleServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {

    RoleServiceModel createRole(RoleServiceModel roleServiceModel);

    void updateRole(RoleServiceModel roleServiceModel);

    RoleServiceViewModel getRoleById(long id);

    List<RoleServiceViewModel> getAleRoles();

    void deleteRoleById(long id);


}

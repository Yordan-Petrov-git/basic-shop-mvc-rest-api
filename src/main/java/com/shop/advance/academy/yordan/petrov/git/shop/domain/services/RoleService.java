package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.RoleServiceModel;
import org.springframework.stereotype.Service;

import java.util.Set;
/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface RoleService {

    /**
     *
     */
    void seedRolesInDatabase();

    /**
     * @return
     */
    Set<RoleServiceModel> findAllRoles();

    /**
     * @param role
     * @return
     */
    RoleServiceModel findByAuthority(String role);
}

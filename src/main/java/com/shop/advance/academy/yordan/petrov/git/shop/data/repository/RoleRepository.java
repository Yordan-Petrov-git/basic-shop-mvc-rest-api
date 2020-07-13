package com.shop.advance.academy.yordan.petrov.git.shop.data.repository;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

}



package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.web.rest.dto.UserLoginModel;
import org.springframework.stereotype.Service;

/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface LoginService {

    /**
     * @param userLoginModel
     */
    void login(UserLoginModel userLoginModel);
}

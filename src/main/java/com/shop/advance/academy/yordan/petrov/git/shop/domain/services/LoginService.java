package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.web.rest.dto.UserLoginModel;

public interface LoginService {

    void login(UserLoginModel userLoginModel);
}

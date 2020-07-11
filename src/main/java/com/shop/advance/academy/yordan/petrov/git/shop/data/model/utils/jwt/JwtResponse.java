package com.shop.advance.academy.yordan.petrov.git.shop.data.model.utils.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
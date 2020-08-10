package com.shop.advance.academy.yordan.petrov.git.shop.data.model.utils.jwt;

import java.io.Serializable;

/**
 * Class  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class JwtResponse implements Serializable {

    private final String jwttoken;

    /**
     * Constructor
     */
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    /**
     * @return
     */
    public String getToken() {
        return this.jwttoken;
    }
}
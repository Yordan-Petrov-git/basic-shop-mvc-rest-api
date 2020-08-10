package com.shop.advance.academy.yordan.petrov.git.shop.data.model.utils.jwt;

import java.io.Serializable;

/**
 * Class  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class JwtRequest implements Serializable {

    private String username;
    private String password;

    /**
     * Constructor
     */
    public JwtRequest() {

    }

    /**
     * Constructor
     */
    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /**
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
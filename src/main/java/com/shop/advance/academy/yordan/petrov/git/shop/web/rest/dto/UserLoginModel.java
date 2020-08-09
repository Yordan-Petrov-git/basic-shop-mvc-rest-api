package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.dto;
/**
 * Class dto for log in.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class UserLoginModel {

    private String username;
    private String password;

    /**
     *
     */
    public UserLoginModel() {
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
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
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

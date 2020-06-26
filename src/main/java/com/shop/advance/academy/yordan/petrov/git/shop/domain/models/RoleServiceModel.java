package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

public class RoleServiceModel {

    private Long id;
    private String authority;

    public RoleServiceModel(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleServiceModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


}

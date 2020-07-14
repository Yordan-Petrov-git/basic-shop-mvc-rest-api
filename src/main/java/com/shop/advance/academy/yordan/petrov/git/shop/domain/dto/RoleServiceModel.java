package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

public class RoleServiceModel {

    private Long id;
    private String authority;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleServiceModel)) return false;
        RoleServiceModel that = (RoleServiceModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleServiceModel{");
        sb.append("id=").append(id);
        sb.append(", authority='").append(authority).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

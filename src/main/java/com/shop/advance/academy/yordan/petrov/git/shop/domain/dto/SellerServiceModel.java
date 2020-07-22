package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellerServiceModel {

    private Long id;
    private String name;
    private List<ItemCountPairServiceModel> stock = new ArrayList<>();
    private UserServiceModel user;


    public SellerServiceModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemCountPairServiceModel> getStock() {
        return this.stock;
    }

    public void setStock(List<ItemCountPairServiceModel> stock) {
        this.stock = stock;
    }

    public UserServiceModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SellerServiceModel)) return false;
        SellerServiceModel that = (SellerServiceModel) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SellerServiceModel{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

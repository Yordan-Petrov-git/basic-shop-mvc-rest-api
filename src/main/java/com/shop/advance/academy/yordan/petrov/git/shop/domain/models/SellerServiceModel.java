package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}

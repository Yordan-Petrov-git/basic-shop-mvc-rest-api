package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceModel {

    private Long id;
    private List<ItemServiceViewModel> addedItems =  new ArrayList<>();
    private UserServiceModel user;


    public ShoppingCartServiceModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemServiceViewModel> getAddedItems() {
        return this.addedItems;
    }

    public void setAddedItems(List<ItemServiceViewModel> addedItems) {
        this.addedItems = addedItems;
    }

    public UserServiceModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}

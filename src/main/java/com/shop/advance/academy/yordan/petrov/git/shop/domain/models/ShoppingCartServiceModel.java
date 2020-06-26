package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceModel {


    private List<ItemServiceViewModel> addedItems =  new ArrayList<>();
    private UserServiceModel user;


    public ShoppingCartServiceModel() {
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

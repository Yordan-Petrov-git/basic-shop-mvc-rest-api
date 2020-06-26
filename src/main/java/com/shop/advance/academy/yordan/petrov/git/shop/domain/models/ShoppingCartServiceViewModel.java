package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceViewModel {


    private List<ItemServiceViewModel> addedItems = new ArrayList<>();
    private UserServiceViewModel user;


    public ShoppingCartServiceViewModel() {
    }

    public List<ItemServiceViewModel> getAddedItems() {
        return this.addedItems;
    }

    public void setAddedItems(List<ItemServiceViewModel> addedItems) {
        this.addedItems = addedItems;
    }

    public UserServiceViewModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceViewModel user) {
        this.user = user;
    }



}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.util.ArrayList;
import java.util.List;

public class SellerServiceViewModel {


    private Long id;
    private String name;
    private List<ItemCountPairServiceViewModel> stock = new ArrayList<>();
    private UserServiceViewModel user;


    public SellerServiceViewModel() {
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

    public List<ItemCountPairServiceViewModel> getStock() {
        return this.stock;
    }

    public void setStock(List<ItemCountPairServiceViewModel> shoppingCartItem) {
        this.stock = shoppingCartItem;
    }


    public UserServiceViewModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceViewModel user) {
        this.user = user;
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceViewModel {

    private Long id;
    private List<ShoppingCartItemServiceViewModel> shoppingCartItem = new ArrayList<>();
    private UserServiceViewModel user;
    private LocalDateTime created;
    private LocalDateTime modified;

    public ShoppingCartServiceViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ShoppingCartItemServiceViewModel> getShoppingCartItem() {
        return this.shoppingCartItem;
    }

    public void setShoppingCartItem(List<ShoppingCartItemServiceViewModel> shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    public UserServiceViewModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceViewModel user) {
        this.user = user;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}

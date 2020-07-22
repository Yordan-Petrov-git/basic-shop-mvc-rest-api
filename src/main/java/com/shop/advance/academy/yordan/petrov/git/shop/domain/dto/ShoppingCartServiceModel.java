package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCartServiceModel {

    private Long id;
    private List<ItemCountPairServiceModel> shoppingCartItem = new ArrayList<>();
    private UserServiceModel user;
    private LocalDateTime created;
    private LocalDateTime modified;
    private BigDecimal totalItemsPrice;

    public ShoppingCartServiceModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemCountPairServiceModel> getItemCountPair() {
        return this.shoppingCartItem;
    }

    public void setItemCountPair(List<ItemCountPairServiceModel> shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    public UserServiceModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceModel user) {
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

    public BigDecimal getTotalItemsPrice() {
        return this.totalItemsPrice;
    }

    public void setTotalItemsPrice(BigDecimal totalItemsPrice) {
        this.totalItemsPrice = totalItemsPrice;
    }

    public List<ItemCountPairServiceModel> getShoppingCartItem() {
        return this.shoppingCartItem;
    }

    public void setShoppingCartItem(List<ItemCountPairServiceModel> shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartServiceModel)) return false;
        ShoppingCartServiceModel that = (ShoppingCartServiceModel) o;
        return Objects.equals(created, that.created) &&
                Objects.equals(modified, that.modified) &&
                Objects.equals(totalItemsPrice, that.totalItemsPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, modified, totalItemsPrice);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingCartServiceModel{");
        sb.append("created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(", totalItemsPrice=").append(totalItemsPrice);
        sb.append('}');
        return sb.toString();
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.math.BigDecimal;

public class ShoppingCartItemServiceModel {

    private ItemServiceModel item;
    private Integer itemCount;


    public ShoppingCartItemServiceModel() {
    }

    public ItemServiceModel getItem() {
        return this.item;
    }

    public void setItem(ItemServiceModel item) {
        this.item = item;
    }

    public Integer getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }


}

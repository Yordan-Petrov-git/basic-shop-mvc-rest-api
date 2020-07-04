package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

public class ShoppingCartItem {

    private Item item;
    private Integer itemCount;


    public ShoppingCartItem(Item item, Integer itemCount) {
        this.item = item;
        this.itemCount = itemCount;
    }

    public ShoppingCartItem() {
    }


    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem extends BaseEntity{

    private Item item;
    private Integer itemCount;

    public ShoppingCartItem(Item item, Integer itemCount) {
        this.item = item;
        this.itemCount = itemCount;
    }

    public ShoppingCartItem() {
    }
    @ManyToOne(fetch = FetchType.LAZY
            ,cascade = {CascadeType.DETACH,CascadeType.REMOVE})
    @JoinColumn(name = "item_id",referencedColumnName = "id")
    public Item getItem() {
        return this.item;
    }
    @Column(name = "item_count")
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

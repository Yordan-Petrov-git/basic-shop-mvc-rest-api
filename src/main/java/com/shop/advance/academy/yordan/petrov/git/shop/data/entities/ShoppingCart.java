package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart extends BaseEntity {


    private List<Item> addedItems = new ArrayList<Item>();
    private User user;


    public ShoppingCart() {
    }


    @ManyToMany(
            targetEntity = Item.class,
            fetch = FetchType.EAGER)
    @JoinTable(name = "shopping_cart_item",
            joinColumns = @JoinColumn(name = "shoping_cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    public List<Item> getAddedItems() {
        return this.addedItems;
    }

    public void setAddedItems(List<Item> addedItems) {
        this.addedItems = addedItems;
    }

    @ManyToOne(targetEntity = User.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCart)) return false;
        if (!super.equals(o)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingCart{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

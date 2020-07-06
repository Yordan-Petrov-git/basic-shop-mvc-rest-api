package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {


    private String name;
    private List<ItemCountPair> stock = new ArrayList<>();
    private User user;

    public Seller() {
    }

    @ManyToOne(fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(targetEntity = ItemCountPair.class,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "seller_item_stock",
            joinColumns = @JoinColumn(name = "seller_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_count_pair_id", referencedColumnName = "id")
    )
    public List<ItemCountPair> getStock() {
        return this.stock;
    }

    public void setStock(List<ItemCountPair> stock) {
        this.stock = stock;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;
        if (!super.equals(o)) return false;
        Seller seller = (Seller) o;
        return Objects.equals(name, seller.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Seller{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}


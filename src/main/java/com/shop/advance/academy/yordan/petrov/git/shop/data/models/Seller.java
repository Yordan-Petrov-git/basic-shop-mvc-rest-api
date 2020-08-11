package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {


    private String name;
    private List<ItemCountPair> stock = new ArrayList<>();
    private User user;

    /**
     * Constructor
     */
    public Seller() {
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToOne(fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    /**
     * Method for
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Method for
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Method for
     *
     * @return
     */
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

    /**
     * Method for
     *
     * @param stock
     */
    public void setStock(List<ItemCountPair> stock) {
        this.stock = stock;
    }


    /**
     * Method for
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;
        if (!super.equals(o)) return false;
        Seller seller = (Seller) o;
        return Objects.equals(name, seller.name);
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Seller{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}


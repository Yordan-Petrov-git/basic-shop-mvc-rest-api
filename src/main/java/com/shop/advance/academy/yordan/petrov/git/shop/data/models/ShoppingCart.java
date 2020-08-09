package com.shop.advance.academy.yordan.petrov.git.shop.data.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "shopping_carts")
public class ShoppingCart extends BaseEntity {

    private List<ItemCountPair> itemCountPair = new ArrayList<>();
    private LocalDateTime created;
    private LocalDateTime modified;
    private User user;
    private BigDecimal totalItemsPrice;

    /**
     * Constructor
     */
    public ShoppingCart() {
    }


    /**
     * Method for
     *
     * @return
     */
    @OneToMany(
            targetEntity = ItemCountPair.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "shopping_cart_item_count_pair",
            joinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_count_pair_item_id", referencedColumnName = "id"))
    public List<ItemCountPair> getItemCountPair() {
        return this.itemCountPair;
    }

    /**
     * Method for
     *
     * @param itemCountPair
     */
    public void setItemCountPair(List<ItemCountPair> itemCountPair) {
        this.itemCountPair = itemCountPair;
    }


    /**
     * Method for
     *
     * @return
     */
    @Column(name = "date_time_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreated() {
        return this.created;
    }

    /**
     * Method for
     *
     * @param created
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "date_time_modified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getModified() {
        return this.modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToOne(targetEntity = User.class,
            cascade = {CascadeType.DETACH},
            fetch = FetchType.LAZY)
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
    public BigDecimal getTotalItemsPrice() {
        return this.totalItemsPrice;
    }

    /**
     * Method for
     *
     * @param totalItemsPrice
     */
    public void setTotalItemsPrice(BigDecimal totalItemsPrice) {
        this.totalItemsPrice = totalItemsPrice;
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
        if (!(o instanceof ShoppingCart)) return false;
        if (!super.equals(o)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(created, that.created) &&
                Objects.equals(modified, that.modified) &&
                Objects.equals(totalItemsPrice, that.totalItemsPrice);
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), created, modified, totalItemsPrice);
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingCart{");
        sb.append("created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(", totalItemsPrice=").append(totalItemsPrice);
        sb.append('}');
        return sb.toString();
    }
}


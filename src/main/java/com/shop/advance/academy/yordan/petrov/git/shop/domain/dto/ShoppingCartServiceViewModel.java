package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class ShoppingCartServiceViewModel {

    private Long id;
    private List<ItemCountPairServiceViewModel> shoppingCartItem = new ArrayList<>();
    private UserServiceViewModel user;
    private LocalDateTime created;
    private LocalDateTime modified;
    private BigDecimal totalItemsPrice;

    /**
     * Constructor
     */
    public ShoppingCartServiceViewModel() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets item count pair.
     *
     * @return the item count pair
     */
    public List<ItemCountPairServiceViewModel> getItemCountPair() {
        return this.shoppingCartItem;
    }

    /**
     * Sets item count pair.
     *
     * @param shoppingCartItem the shopping cart item
     */
    public void setItemCountPair(List<ItemCountPairServiceViewModel> shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserServiceViewModel getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserServiceViewModel user) {
        this.user = user;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public LocalDateTime getCreated() {
        return this.created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * Gets modified.
     *
     * @return the modified
     */
    public LocalDateTime getModified() {
        return this.modified;
    }

    /**
     * Sets modified.
     *
     * @param modified the modified
     */
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    /**
     * Gets total items price.
     *
     * @return the total items price
     */
    public BigDecimal getTotalItemsPrice() {
        return this.totalItemsPrice;
    }

    /**
     * Sets total items price.
     *
     * @param totalItemsPrice the total items price
     */
    public void setTotalItemsPrice(BigDecimal totalItemsPrice) {
        this.totalItemsPrice = totalItemsPrice;
    }

    /**
     * Gets shopping cart item.
     *
     * @return the shopping cart item
     */
    public List<ItemCountPairServiceViewModel> getShoppingCartItem() {
        return this.shoppingCartItem;
    }

    /**
     * Sets shopping cart item.
     *
     * @param shoppingCartItem the shopping cart item
     */
    public void setShoppingCartItem(List<ItemCountPairServiceViewModel> shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartServiceViewModel)) return false;
        ShoppingCartServiceViewModel that = (ShoppingCartServiceViewModel) o;
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
        final StringBuilder sb = new StringBuilder("ShoppingCartServiceViewModel{");
        sb.append("created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(", totalItemsPrice=").append(totalItemsPrice);
        sb.append('}');
        return sb.toString();
    }
}

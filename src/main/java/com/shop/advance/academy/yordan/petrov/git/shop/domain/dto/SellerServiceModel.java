package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

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
public class SellerServiceModel {

    private Long id;
    private String name;
    private List<ItemCountPairServiceModel> stock = new ArrayList<>();
    private UserServiceModel user;

    /**
     * Constructor
     */
    public SellerServiceModel() {
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    public List<ItemCountPairServiceModel> getStock() {
        return this.stock;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public void setStock(List<ItemCountPairServiceModel> stock) {
        this.stock = stock;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserServiceModel getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SellerServiceModel)) return false;
        SellerServiceModel that = (SellerServiceModel) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SellerServiceModel{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

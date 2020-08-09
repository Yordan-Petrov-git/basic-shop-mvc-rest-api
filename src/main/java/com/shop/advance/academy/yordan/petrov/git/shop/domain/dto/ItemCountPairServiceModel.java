package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;
/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class ItemCountPairServiceModel {

    private ItemServiceModel item;
    private Integer itemCount;

    /**
     * Constructor
     */
    public ItemCountPairServiceModel() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCountPairServiceModel)) return false;
        ItemCountPairServiceModel that = (ItemCountPairServiceModel) o;
        return Objects.equals(itemCount, that.itemCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemCountPairServiceModel{");
        sb.append("itemCount=").append(itemCount);
        sb.append('}');
        return sb.toString();
    }
}

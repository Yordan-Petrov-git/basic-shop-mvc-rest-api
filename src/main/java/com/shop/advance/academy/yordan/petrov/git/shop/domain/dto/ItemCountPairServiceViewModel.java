package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class ItemCountPairServiceViewModel {

    private ItemServiceViewModel item;
    private Integer itemCount;

    /**
     * Constructor
     */
    public ItemCountPairServiceViewModel() {
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public ItemServiceViewModel getItem() {
        return this.item;
    }

    /**
     * Sets item.
     *
     * @param item the item
     */
    public void setItem(ItemServiceViewModel item) {
        this.item = item;
    }

    /**
     * Gets item count.
     *
     * @return the item count
     */
    public Integer getItemCount() {
        return this.itemCount;
    }

    /**
     * Sets item count.
     *
     * @param itemCount the item count
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCountPairServiceViewModel)) return false;
        ItemCountPairServiceViewModel that = (ItemCountPairServiceViewModel) o;
        return Objects.equals(itemCount, that.itemCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemCountPairServiceViewModel{");
        sb.append("itemCount=").append(itemCount);
        sb.append('}');
        return sb.toString();
    }
}







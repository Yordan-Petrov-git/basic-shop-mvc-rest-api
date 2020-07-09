package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.util.Objects;

public class ItemCountPairServiceViewModel {

    private ItemServiceViewModel item;
    private Integer itemCount;


    public ItemCountPairServiceViewModel() {
    }

    public ItemServiceViewModel getItem() {
        return this.item;
    }

    public void setItem(ItemServiceViewModel item) {
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







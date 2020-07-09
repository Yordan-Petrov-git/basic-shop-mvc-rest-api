package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.util.Objects;

public class ItemCountPairServiceModel {

    private ItemServiceModel item;
    private Integer itemCount;


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

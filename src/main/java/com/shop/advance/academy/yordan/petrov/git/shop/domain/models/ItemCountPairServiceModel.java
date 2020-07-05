package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

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


}

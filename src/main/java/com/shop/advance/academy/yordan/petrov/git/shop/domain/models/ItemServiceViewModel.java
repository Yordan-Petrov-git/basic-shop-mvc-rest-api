package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemServiceViewModel {

    private MediaServiceViewModel media;
    private List<OpinionServiceViewModel> opinions = new ArrayList<>();
    private String title;
    private String description;
    private BigDecimal price;
    private Double weight;
    private BigDecimal vat;
    private ItemCategory itemCategory = ItemCategory.NONE;

    public ItemServiceViewModel() {
    }


    public ItemServiceViewModel(MediaServiceViewModel media, List<OpinionServiceViewModel> opinions,
                                String title, String description, BigDecimal price, Double weight, BigDecimal vat,
                                ItemCategory itemCategory) {
        this.media = media;
        this.opinions = opinions;
        this.title = title;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.vat = vat;
        this.itemCategory = itemCategory;
    }


    public MediaServiceViewModel getMedia() {
        return this.media;
    }

    public void setMedia(MediaServiceViewModel media) {
        this.media = media;
    }

    public List<OpinionServiceViewModel> getOpinions() {
        return this.opinions;
    }

    public void setOpinions(List<OpinionServiceViewModel> opinions) {
        this.opinions = opinions;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public BigDecimal getVat() {
        return this.vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public ItemCategory getItemCategory() {
        return this.itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemServiceViewModel)) return false;
        ItemServiceViewModel that = (ItemServiceViewModel) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(vat, that.vat) &&
                itemCategory == that.itemCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, weight, vat, itemCategory);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemServiceViewModel{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", vat=").append(vat);
        sb.append(", itemCategory=").append(itemCategory);
        sb.append('}');
        return sb.toString();
    }
}

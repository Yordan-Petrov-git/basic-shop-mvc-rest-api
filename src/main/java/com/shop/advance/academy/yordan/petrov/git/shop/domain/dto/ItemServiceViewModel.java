package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.ItemCategory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ItemServiceViewModel {

    private Long id;
    private Set<MediaServiceViewModel> media = new HashSet<>();
    private Set<OpinionServiceViewModel> opinions = new HashSet<>();
    private String title;
    private String description;
    private BigDecimal price;
    private Double weight;
    private ItemCategory itemCategory = ItemCategory.NONE;

    public ItemServiceViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MediaServiceViewModel> getMedia() {
        return this.media;
    }

    public void setMedia(Set<MediaServiceViewModel> media) {
        this.media = media;
    }

    public Set<OpinionServiceViewModel> getOpinions() {
        return this.opinions;
    }

    public void setOpinions(Set<OpinionServiceViewModel> opinions) {
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
                itemCategory == that.itemCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, weight, itemCategory);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemServiceViewModel{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", itemCategory=").append(itemCategory);
        sb.append('}');
        return sb.toString();
    }
}

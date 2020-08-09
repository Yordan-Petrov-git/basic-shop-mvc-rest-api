package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.ItemCategory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class ItemServiceModel {


    private Long id;
    private Set<MediaServiceModel> media = new HashSet<>();
    private Set<OpinionServiceModel> opinions = new HashSet<>();
    private String title;
    private String description;
    private BigDecimal price;
    private Double weight;
    private ItemCategory itemCategory = ItemCategory.NONE;

    /**
     * Constructor
     */
    public ItemServiceModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MediaServiceModel> getMedia() {
        return this.media;
    }

    public void setMedia(Set<MediaServiceModel> media) {
        this.media = media;
    }

    public Set<OpinionServiceModel> getOpinions() {
        return this.opinions;
    }

    public void setOpinions(Set<OpinionServiceModel> opinions) {
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
        if (!(o instanceof ItemServiceModel)) return false;
        ItemServiceModel that = (ItemServiceModel) o;
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
        final StringBuilder sb = new StringBuilder("ItemServiceModel{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", itemCategory=").append(itemCategory);
        sb.append('}');
        return sb.toString();
    }
}



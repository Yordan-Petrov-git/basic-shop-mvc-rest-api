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
public class ItemServiceViewModel {

    private Long id;
    private Set<MediaServiceViewModel> media = new HashSet<>();
    private Set<OpinionServiceViewModel> opinions = new HashSet<>();
    private String title;
    private String description;
    private BigDecimal price;
    private Double weight;
    private ItemCategory itemCategory = ItemCategory.NONE;

    /**
     * Constructor
     */
    public ItemServiceViewModel() {
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
     * Gets media.
     *
     * @return the media
     */
    public Set<MediaServiceViewModel> getMedia() {
        return this.media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(Set<MediaServiceViewModel> media) {
        this.media = media;
    }

    /**
     * Gets opinions.
     *
     * @return the opinions
     */
    public Set<OpinionServiceViewModel> getOpinions() {
        return this.opinions;
    }

    /**
     * Sets opinions.
     *
     * @param opinions the opinions
     */
    public void setOpinions(Set<OpinionServiceViewModel> opinions) {
        this.opinions = opinions;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Double getWeight() {
        return this.weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }


    /**
     * Gets item category.
     *
     * @return the item category
     */
    public ItemCategory getItemCategory() {
        return this.itemCategory;
    }

    /**
     * Sets item category.
     *
     * @param itemCategory the item category
     */
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

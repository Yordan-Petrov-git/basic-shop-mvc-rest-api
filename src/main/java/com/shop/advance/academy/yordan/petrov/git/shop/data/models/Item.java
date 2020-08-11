package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.ItemCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    private String title;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Set<Media> media = new HashSet<>();
    private Set<Opinion> opinions = new HashSet<>();
    private ItemCategory itemCategory = ItemCategory.NONE;

    /**
     * Constructor
     */
    public Item() {
    }

    /**
     * @return
     */
    @OneToMany(targetEntity = Media.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinTable(name = "items_media",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "media_id", referencedColumnName = "id"))
    public Set<Media> getMedia() {
        return this.media;
    }

    /**
     * @param media
     */
    public void setMedia(Set<Media> media) {
        this.media = media;
    }

    /**
     * @return
     */
    @OneToMany(targetEntity = Opinion.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinTable(name = "item_opinion",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "opinion_id", referencedColumnName = "id"))
    public Set<Opinion> getOpinions() {
        return this.opinions;
    }

    /**
     * @param opinions
     */
    public void setOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }

    /**
     * @return
     */
    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     */
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     */
    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return
     */
    @Column(name = "weight")
    public Double getWeight() {
        return this.weight;
    }

    /**
     * @param weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }


    /**
     * @return
     */
    @Column(name = "item_category")
    @Enumerated(EnumType.STRING)
    public ItemCategory getItemCategory() {
        return this.itemCategory;
    }

    /**
     * @param itemCategory
     */
    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(title, item.title) &&
                Objects.equals(description, item.description) &&
                Objects.equals(price, item.price) &&
                Objects.equals(weight, item.weight) &&
                itemCategory == item.itemCategory;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, price, weight, itemCategory);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", itemCategory=").append(itemCategory);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.ItemCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    private String title;   
    private String description;
    private BigDecimal price;
    private Double weight;
    private BigDecimal vat;
    private Set<Media> media= new HashSet<>();
    private Set<Opinion> opinions = new HashSet<>();
    private ItemCategory itemCategory = ItemCategory.NONE;

    public Item() {
    }

    @OneToMany(targetEntity = Media.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval = true)
    @JoinTable(name = "items_media",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "media_id", referencedColumnName = "id"))
    public Set<Media> getMedia() {
        return this.media;
    }

    public void setMedia(Set<Media> media) {
        this.media = media;
    }

    @OneToMany(targetEntity = Opinion.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @JoinTable(name = "item_opinion",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "opinion_id", referencedColumnName = "id"))
    public Set<Opinion> getOpinions() {
        return this.opinions;
    }

    public void setOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }

    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "weight")
    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Column(name = "vat")
    public BigDecimal getVat() {
        return this.vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    @Column(name = "item_category")
    @Enumerated(EnumType.STRING)
    public ItemCategory getItemCategory() {
        return this.itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

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
                Objects.equals(vat, item.vat) &&
                itemCategory == item.itemCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, price, weight, vat, itemCategory);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", vat=").append(vat);
        sb.append(", itemCategory=").append(itemCategory);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

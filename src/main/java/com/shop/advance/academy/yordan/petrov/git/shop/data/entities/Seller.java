package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {


    private String name;
    private Set<Item> stock = new HashSet<>();
    private ContactInformation contactInformation;
    private Set<Address> addresses = new HashSet<>();

    public Seller() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(targetEntity = Item.class,
            cascade = {CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "seller_item",
            joinColumns = @JoinColumn(name = "seller_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    public Set<Item> getStock() {
        return this.stock;
    }

    public void setStock(Set<Item> stock) {
        this.stock = stock;
    }


    @ManyToOne(targetEntity = ContactInformation.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_information_id", referencedColumnName = "id")
    public ContactInformation getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @ManyToMany(targetEntity = Address.class,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_address",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id")
    )
    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;
        if (!super.equals(o)) return false;
        Seller seller = (Seller) o;
        return Objects.equals(name, seller.name) &&
                Objects.equals(contactInformation, seller.contactInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, contactInformation);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Seller{");
        sb.append("name='").append(name).append('\'');
        sb.append(", contactInformation=").append(contactInformation);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}


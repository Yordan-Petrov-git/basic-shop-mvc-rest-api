package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    private String streetNumber;
    private String streetName;
    private City city;


    public Address() {
    }

    @Column(name = "street_number")
    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Column(name = "street_name")
    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @ManyToOne(targetEntity = City.class
            , cascade = {CascadeType.DETACH}
            , fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id"
            , referencedColumnName = "id")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(streetName, address.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), streetNumber, streetName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("streetNumber='").append(streetNumber).append('\'');
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    private String streetNumber;
    private String streetName;
    private City city;
    private List<User> users = new ArrayList<>();

    /**
     * Constructor
     */
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
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    @ManyToMany(targetEntity = User.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "addres_users",
            joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

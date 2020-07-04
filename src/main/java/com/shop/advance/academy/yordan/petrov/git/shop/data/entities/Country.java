package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    private String name;
    private String coordinates;


    public Country() {
    }

    @Column(name = "country_name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "coordinates")
    public String getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) &&
                Objects.equals(coordinates, country.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, coordinates);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("name='").append(name).append('\'');
        sb.append(", coordinates='").append(coordinates).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}



package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class CountryServiceViewModel {
    private Long id;
    private String name;
    private String coordinates;

    /**
     * Constructor
     */
    public CountryServiceViewModel() {
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public String getCoordinates() {
        return this.coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryServiceViewModel)) return false;
        CountryServiceViewModel that = (CountryServiceViewModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CountryServiceViewModel{");
        sb.append("name='").append(name).append('\'');
        sb.append(", coordinates='").append(coordinates).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

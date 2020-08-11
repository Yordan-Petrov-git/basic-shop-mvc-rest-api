package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class AddressServiceViewModel {

    private Long id;
    private String streetNumber;
    private String streetName;
    private CityServiceViewModel city;
    private List<UserServiceViewModel> user = new ArrayList<>();

    /**
     * Constructor
     */
    public AddressServiceViewModel() {
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
     * Gets street number.
     *
     * @return the street number
     */
    public String getStreetNumber() {
        return this.streetNumber;
    }

    /**
     * Sets street number.
     *
     * @param streetNumber the street number
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Gets street name.
     *
     * @return the street name
     */
    public String getStreetName() {
        return this.streetName;
    }

    /**
     * Sets street name.
     *
     * @param streetName the street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public CityServiceViewModel getCity() {
        return this.city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(CityServiceViewModel city) {
        this.city = city;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public List<UserServiceViewModel> getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(List<UserServiceViewModel> user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressServiceViewModel)) return false;
        AddressServiceViewModel that = (AddressServiceViewModel) o;
        return Objects.equals(streetNumber, that.streetNumber) &&
                Objects.equals(streetName, that.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetNumber, streetName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddressServiceViewModel{");
        sb.append("streetNumber='").append(streetNumber).append('\'');
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

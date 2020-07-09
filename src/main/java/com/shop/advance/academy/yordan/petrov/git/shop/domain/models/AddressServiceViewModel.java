package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressServiceViewModel {

    private Long id;
    private String streetNumber;
    private String streetName;
    private CityServiceViewModel city;
    private List<UserServiceViewModel> user = new ArrayList<>();

    public AddressServiceViewModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public CityServiceViewModel getCity() {
        return this.city;
    }

    public void setCity(CityServiceViewModel city) {
        this.city = city;
    }

    public List<UserServiceViewModel> getUser() {
        return this.user;
    }

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

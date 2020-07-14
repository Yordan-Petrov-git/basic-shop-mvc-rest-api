package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressServiceModel {

    private Long id;
    private String streetNumber;
    private String streetName;
    private CityServiceModel city;
    private List<UserServiceModel> users = new ArrayList<>();

    public AddressServiceModel() {
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

    public CityServiceModel getCity() {
        return this.city;
    }

    public void setCity(CityServiceModel city) {
        this.city = city;
    }

    public List<UserServiceModel> getUser() {
        return this.users;
    }

    public void setUser(List<UserServiceModel> user) {
        this.users = user;
    }

    public List<UserServiceModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserServiceModel> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressServiceModel)) return false;
        AddressServiceModel that = (AddressServiceModel) o;
        return Objects.equals(streetNumber, that.streetNumber) &&
                Objects.equals(streetName, that.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetNumber, streetName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddressServiceModel{");
        sb.append("streetNumber='").append(streetNumber).append('\'');
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append('}');
        return sb.toString();
    }


}

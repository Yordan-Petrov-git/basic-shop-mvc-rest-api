package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.City;

public class AddressServiceModel {

    private String streetNumber;
    private String streetName;
    private CityServiceModel city;


    public AddressServiceModel() {
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
}

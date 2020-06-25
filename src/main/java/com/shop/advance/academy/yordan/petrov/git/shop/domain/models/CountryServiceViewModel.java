package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

public class CountryServiceViewModel {

    private String name;
    private String coordinates;


    public CountryServiceViewModel() {
    }


    public CountryServiceViewModel(String name, String coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}

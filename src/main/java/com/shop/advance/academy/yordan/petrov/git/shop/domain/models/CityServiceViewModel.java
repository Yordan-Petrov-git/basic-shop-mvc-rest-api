package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

public class CityServiceViewModel {

    private Long id;
    private String name;
    private CountryServiceModel country;
    private String region;
    private String province;

    public CityServiceViewModel() {
    }

    public CityServiceViewModel(Long id, String name, CountryServiceModel country, String region, String province) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.province = province;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryServiceModel getCountry() {
        return this.country;
    }

    public void setCountry(CountryServiceModel country) {
        this.country = country;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

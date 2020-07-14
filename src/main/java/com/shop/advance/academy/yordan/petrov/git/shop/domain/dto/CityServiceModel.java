package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

public class CityServiceModel {

    private Long id;
    private String name;
    private CountryServiceModel country;
    private String region;
    private String province;

    public CityServiceModel() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityServiceModel)) return false;
        CityServiceModel that = (CityServiceModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(region, that.region) &&
                Objects.equals(province, that.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region, province);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CityServiceModel{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

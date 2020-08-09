package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class CityServiceModel {

    private Long id;
    private String name;
    private CountryServiceModel country;
    private String region;
    private String province;

    /**
     * Constructor
     */
    public CityServiceModel() {
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
     * Gets country.
     *
     * @return the country
     */
    public CountryServiceModel getCountry() {
        return this.country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(CountryServiceModel country) {
        this.country = country;
    }

    /**
     * Gets region.
     *
     * @return the region
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets province.
     *
     * @return the province
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Sets province.
     *
     * @param province the province
     */
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

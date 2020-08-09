package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;
/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class CityServiceViewModel {

    private Long id;
    private String name;
    private CountryServiceViewModel country;
    private String region;
    private String province;

    /**
     * Constructor
     */
    public CityServiceViewModel() {
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

    public CountryServiceViewModel getCountry() {
        return this.country;
    }

    public void setCountry(CountryServiceViewModel country) {
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
        if (!(o instanceof CityServiceViewModel)) return false;
        CityServiceViewModel that = (CityServiceViewModel) o;
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
        final StringBuilder sb = new StringBuilder("CityServiceViewModel{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

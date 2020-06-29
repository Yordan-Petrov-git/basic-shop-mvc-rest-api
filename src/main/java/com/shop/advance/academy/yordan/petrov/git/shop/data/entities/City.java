package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{

    private String name;
    private String region;
    private String province;
    private Country country;


    public City() {
    }
    @Column(name = "city_name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne(targetEntity = Country.class
            , cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
            , fetch = FetchType.EAGER
    )
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    @Column(name = "region")
    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    @Column(name = "province")
    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        if (!super.equals(o)) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) &&
                Objects.equals(region, city.region) &&
                Objects.equals(province, city.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, region, province);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("name='").append(name).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    private String name;
    private String region;
    private String province;
    private Country country;

    /**
     * Constructor
     */
    public City() {
    }

    /**
     * @return
     */
    @Column(name = "city_name")
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    @ManyToOne(targetEntity = Country.class
            , cascade = {CascadeType.DETACH}
            , fetch = FetchType.LAZY
    )
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Country getCountry() {
        return this.country;
    }

    /**
     * @param country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return
     */
    @Column(name = "region")
    public String getRegion() {
        return this.region;
    }

    /**
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return
     */
    @Column(name = "province")
    public String getProvince() {
        return this.province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }


    /**
     * @param o
     * @return
     */
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

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, region, province);
    }


    /**
     * @return
     */
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

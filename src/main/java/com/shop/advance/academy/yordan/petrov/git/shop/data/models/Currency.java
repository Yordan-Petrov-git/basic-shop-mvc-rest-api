package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

    private String name;
    private String iso2;
    private String iso3;
    private Integer multiplierForCurrency;

    /**
     * Constructor
     */
    public Currency() {
    }

    /**
     * @return
     */
    @Column(name = "currency_name")
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
    @Column(name = "iso_2")
    public String getIso2() {
        return this.iso2;
    }

    /**
     * @param iso2
     */
    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    /**
     * @return
     */
    @Column(name = "iso_3")
    public String getIso3() {
        return this.iso3;
    }

    /**
     * @param iso3
     */
    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    /**
     * @return
     */
    @Column(name = "multiplier")
    public Integer getMultiplierForCurrency() {
        return this.multiplierForCurrency;
    }

    /**
     * @param multiplierForCurrency
     */
    public void setMultiplierForCurrency(Integer multiplierForCurrency) {
        this.multiplierForCurrency = multiplierForCurrency;
    }


    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        if (!super.equals(o)) return false;
        Currency currency = (Currency) o;
        return Objects.equals(name, currency.name) &&
                Objects.equals(iso2, currency.iso2) &&
                Objects.equals(iso3, currency.iso3) &&
                Objects.equals(multiplierForCurrency, currency.multiplierForCurrency);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, iso2, iso3, multiplierForCurrency);
    }


    /**
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Currency{");
        sb.append("name='").append(name).append('\'');
        sb.append(", iso2='").append(iso2).append('\'');
        sb.append(", iso3='").append(iso3).append('\'');
        sb.append(", multiplierForCurrency=").append(multiplierForCurrency);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

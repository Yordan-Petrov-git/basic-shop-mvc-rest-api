package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class CurrencyServiceViewModel {
    private Long id;
    private String name;
    private String iso2;
    private String iso3;
    private Integer multiplierForCurrency;

    /**
     * Constructor
     */
    public CurrencyServiceViewModel() {
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
     * Gets iso 2.
     *
     * @return the iso 2
     */
    public String getIso2() {
        return this.iso2;
    }

    /**
     * Sets iso 2.
     *
     * @param iso2 the iso 2
     */
    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    /**
     * Gets iso 3.
     *
     * @return the iso 3
     */
    public String getIso3() {
        return this.iso3;
    }

    /**
     * Sets iso 3.
     *
     * @param iso3 the iso 3
     */
    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    /**
     * Gets multiplier for currency.
     *
     * @return the multiplier for currency
     */
    public Integer getMultiplierForCurrency() {
        return this.multiplierForCurrency;
    }

    /**
     * Sets multiplier for currency.
     *
     * @param multiplierForCurrency the multiplier for currency
     */
    public void setMultiplierForCurrency(Integer multiplierForCurrency) {
        this.multiplierForCurrency = multiplierForCurrency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyServiceViewModel)) return false;
        CurrencyServiceViewModel that = (CurrencyServiceViewModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(iso2, that.iso2) &&
                Objects.equals(iso3, that.iso3) &&
                Objects.equals(multiplierForCurrency, that.multiplierForCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, iso2, iso3, multiplierForCurrency);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrencyServiceViewModel{");
        sb.append("name='").append(name).append('\'');
        sb.append(", iso2='").append(iso2).append('\'');
        sb.append(", iso3='").append(iso3).append('\'');
        sb.append(", multiplierForCurrency=").append(multiplierForCurrency);
        sb.append('}');
        return sb.toString();
    }
}



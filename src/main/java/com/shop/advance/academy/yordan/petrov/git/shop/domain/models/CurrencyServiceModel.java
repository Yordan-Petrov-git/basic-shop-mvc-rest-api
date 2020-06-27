package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import java.util.Objects;

public class CurrencyServiceModel {

    private Long id;
    private String name;
    private String iso2;
    private String iso3;
    private Integer multiplierForCurrency;


    public CurrencyServiceModel() {
    }


    public CurrencyServiceModel(Long id, String name, String iso2, String iso3, Integer multiplierForCurrency) {
        this.id = id;
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.multiplierForCurrency = multiplierForCurrency;
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

    public String getIso2() {
        return this.iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return this.iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Integer getMultiplierForCurrency() {
        return this.multiplierForCurrency;
    }

    public void setMultiplierForCurrency(Integer multiplierForCurrency) {
        this.multiplierForCurrency = multiplierForCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyServiceModel)) return false;
        CurrencyServiceModel that = (CurrencyServiceModel) o;
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
        final StringBuilder sb = new StringBuilder("CurrencyServiceModel{");
        sb.append("name='").append(name).append('\'');
        sb.append(", iso2='").append(iso2).append('\'');
        sb.append(", iso3='").append(iso3).append('\'');
        sb.append(", multiplierForCurrency=").append(multiplierForCurrency);
        sb.append('}');
        return sb.toString();
    }
}


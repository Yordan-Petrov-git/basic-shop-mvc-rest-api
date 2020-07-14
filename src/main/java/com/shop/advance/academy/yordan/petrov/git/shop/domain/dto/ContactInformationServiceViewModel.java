package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

public class ContactInformationServiceViewModel {


    private Long id;
    private String email;
    private String phoneNumber;
    private String countryCode;


    public ContactInformationServiceViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactInformationServiceViewModel)) return false;
        ContactInformationServiceViewModel that = (ContactInformationServiceViewModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phoneNumber, countryCode);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactInformationServiceViewModel{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

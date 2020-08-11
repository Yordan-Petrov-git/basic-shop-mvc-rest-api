package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "contact_informations")
public class ContactInformation extends BaseEntity {

    private String email;
    private String phoneNumber;
    private String countryCode;

    /**
     * Constructor
     */
    public ContactInformation() {
    }

    /**
     * @return
     */
    @Email()
    @Column(name = "email", unique = true)
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    @Pattern(regexp = "^[0-9]{10}$")
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return
     */
    @Column(name = "country_code")
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactInformation)) return false;
        if (!super.equals(o)) return false;
        ContactInformation that = (ContactInformation) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(countryCode, that.countryCode);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, phoneNumber, countryCode);
    }

    /**
     * @return ¶
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactInformation{");
        sb.append("email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

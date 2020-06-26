package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.UserType;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserServiceViewModel {

    private String username;
    private String password;
    private UserType userType;
    private Instant dateRegistered;
    private Date dateOfBirth;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Set<AddressServiceModel> addresses = new HashSet<>();
    private Set<ContactInformationServiceViewModel> contactInformation = new HashSet<>();



    public UserServiceViewModel() {
    }

    public UserServiceViewModel(String username, String password, UserType userType,
                                Instant dateRegistered, Date dateOfBirth, String firstName, String
                                        lastName, String phone, String email, Set<AddressServiceModel> addresses,
                                Set<ContactInformationServiceViewModel> contactInformation) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.dateRegistered = dateRegistered;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.addresses = addresses;
        this.contactInformation = contactInformation;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Instant getDateRegistered() {
        return this.dateRegistered;
    }

    public void setDateRegistered(Instant dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AddressServiceModel> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<AddressServiceModel> addresses) {
        this.addresses = addresses;
    }

    public Set<ContactInformationServiceViewModel> getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(Set<ContactInformationServiceViewModel> contactInformation) {
        this.contactInformation = contactInformation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserServiceViewModel)) return false;
        UserServiceViewModel that = (UserServiceViewModel) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                userType == that.userType &&
                Objects.equals(dateRegistered, that.dateRegistered) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, userType, dateRegistered, dateOfBirth, firstName, lastName, phone, email);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserServiceViewModel{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userType=").append(userType);
        sb.append(", dateRegistered=").append(dateRegistered);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

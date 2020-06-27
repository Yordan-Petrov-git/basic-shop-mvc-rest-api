package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.UserType;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserServiceViewModel {


    private Long id;
    private String username;
    private UserType userType;
    private Instant dateRegistered;
    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;
    private Set<AddressServiceModel> addresses = new HashSet<>();
    private Set<ContactInformationServiceViewModel> contactInformation = new HashSet<>();

    public UserServiceViewModel(Long id, String username, UserType userType, Instant dateRegistered,
                                LocalDate dateOfBirth, String firstName, String lastName, Set<AddressServiceModel> addresses,
                                Set<ContactInformationServiceViewModel> contactInformation) {
        this.id = id;
        this.username = username;
        this.userType = userType;
        this.dateRegistered = dateRegistered;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
        this.contactInformation = contactInformation;
    }

    public UserServiceViewModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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
                userType == that.userType &&
                Objects.equals(dateRegistered, that.dateRegistered) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, userType, dateRegistered, dateOfBirth, firstName, lastName);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserServiceViewModel{");
        sb.append("username='").append(username).append('\'');
        sb.append(", userType=").append(userType);
        sb.append(", dateRegistered=").append(dateRegistered);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

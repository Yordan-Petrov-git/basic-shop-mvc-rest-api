package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;


import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserServiceViewModel {


    private Long id;
    private String username;
    private UserType userType;
    private LocalDate dateOfBirth;
    private LocalDateTime created;
    private String firstName;
    private String lastName;
    private Set<ContactInformationServiceViewModel> contactInformation = new HashSet<>();


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

    public Set<ContactInformationServiceViewModel> getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(Set<ContactInformationServiceViewModel> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserServiceViewModel)) return false;
        UserServiceViewModel that = (UserServiceViewModel) o;
        return Objects.equals(username, that.username) &&
                userType == that.userType &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, userType, dateOfBirth, firstName, lastName);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserServiceViewModel{");
        sb.append("username='").append(username).append('\'');
        sb.append(", userType=").append(userType);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

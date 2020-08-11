package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;


import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class UserServiceViewModel {


    private Long id;
    private String username;
    private UserType userType;
    private LocalDate dateOfBirth;
    private LocalDateTime created;
    private String firstName;
    private String lastName;
    private Set<ContactInformationServiceViewModel> contactInformation = new HashSet<>();

    /**
     * Constructor
     */
    public UserServiceViewModel() {
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
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public UserType getUserType() {
        return this.userType;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets contact information.
     *
     * @return the contact information
     */
    public Set<ContactInformationServiceViewModel> getContactInformation() {
        return this.contactInformation;
    }

    /**
     * Sets contact information.
     *
     * @param contactInformation the contact information
     */
    public void setContactInformation(Set<ContactInformationServiceViewModel> contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public LocalDateTime getCreated() {
        return this.created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
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

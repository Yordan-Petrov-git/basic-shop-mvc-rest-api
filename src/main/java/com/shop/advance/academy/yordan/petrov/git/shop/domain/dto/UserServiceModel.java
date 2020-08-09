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
public class UserServiceModel {

    private Long id;

    private String username;
    private String password;
    private UserType userType = UserType.REGULAR;
    private LocalDate dateOfBirth;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String firstName;
    private String lastName;
    private Set<ContactInformationServiceModel> contactInformation = new HashSet<>();
    private Set<RoleServiceModel> roles = new HashSet<>();
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isAccountNonExpired = true;

    /**
     * Constructor
     */
    public UserServiceModel() {
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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


    /**
     * Gets modified.
     *
     * @return the modified
     */
    public LocalDateTime getModified() {
        return this.modified;
    }

    /**
     * Sets modified.
     *
     * @param modified the modified
     */
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
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
    public Set<ContactInformationServiceModel> getContactInformation() {
        return this.contactInformation;
    }

    /**
     * Sets contact information.
     *
     * @param contactInformation the contact information
     */
    public void setContactInformation(Set<ContactInformationServiceModel> contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<RoleServiceModel> getRoles() {
        return this.roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
    }

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    public boolean isEnabled() {
        return this.isEnabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    /**
     * Is credentials non expired boolean.
     *
     * @return the boolean
     */
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    /**
     * Sets credentials non expired.
     *
     * @param credentialsNonExpired the credentials non expired
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    /**
     * Is account non locked boolean.
     *
     * @return the boolean
     */
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    /**
     * Sets account non locked.
     *
     * @param accountNonLocked the account non locked
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    /**
     * Is account non expired boolean.
     *
     * @return the boolean
     */
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    /**
     * Sets account non expired.
     *
     * @param accountNonExpired the account non expired
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserServiceModel)) return false;
        UserServiceModel that = (UserServiceModel) o;
        return isEnabled == that.isEnabled &&
                isCredentialsNonExpired == that.isCredentialsNonExpired &&
                isAccountNonLocked == that.isAccountNonLocked &&
                isAccountNonExpired == that.isAccountNonExpired &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                userType == that.userType &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(created, that.created) &&
                Objects.equals(modified, that.modified) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, userType, dateOfBirth, created, modified, firstName,
                lastName, isEnabled, isCredentialsNonExpired, isAccountNonLocked, isAccountNonExpired);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserServiceModel{");

        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userType=").append(userType);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", isCredentialsNonExpired=").append(isCredentialsNonExpired);
        sb.append(", isAccountNonLocked=").append(isAccountNonLocked);
        sb.append(", isAccountNonExpired=").append(isAccountNonExpired);
        sb.append('}');
        return sb.toString();
    }
}

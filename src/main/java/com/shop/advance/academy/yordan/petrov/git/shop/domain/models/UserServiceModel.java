package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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


    public UserServiceModel() {
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


    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    public LocalDateTime getModified() {
        return this.modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
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

    public Set<ContactInformationServiceModel> getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(Set<ContactInformationServiceModel> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<RoleServiceModel> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

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

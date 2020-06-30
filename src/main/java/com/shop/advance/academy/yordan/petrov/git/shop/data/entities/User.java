package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.UserType;
import io.micrometer.core.lang.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private String username;
    private String password;
    private UserType userType;
    private LocalDate dateOfBirth;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String firstName;
    private String lastName;
    private Set<Address> addresses = new HashSet<>();
    private Set<Card> cards = new HashSet<>();
    private Set<ContactInformation> contactInformation = new HashSet<>();
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isAccountNonExpired = true;
    private Set<Role> authorities = new HashSet<>();


    public User() {
    }


    @Override
    @NonNull
    @NotEmpty(message = "Username cannot be empty")
    @Length(min = 2, max = 128, message = "Username must be at least 2 characters")
    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @NonNull
    @NotEmpty
    @Length(min = 8, max = 128, message = "password must be at least 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", unique = true, nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Column(name = "date_of_birth")
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified")
    public LocalDateTime getModified() {
        return this.modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @ManyToMany(targetEntity = Address.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_address",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id")
    )
    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }


    @OneToMany(targetEntity = Card.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_cards",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"))
    public Set<Card> getCards() {
        return this.cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }


    @OneToMany(targetEntity = ContactInformation.class
            , fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true)
    @JoinTable(name = "user_contact_information",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_information_id", referencedColumnName = "id"))
    public Set<ContactInformation> getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(Set<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    @ManyToMany(targetEntity = Role.class,
            fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return isEnabled == user.isEnabled &&
                isCredentialsNonExpired == user.isCredentialsNonExpired &&
                isAccountNonLocked == user.isAccountNonLocked &&
                isAccountNonExpired == user.isAccountNonExpired &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                userType == user.userType &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(created, user.created) &&
                Objects.equals(modified, user.modified) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, userType, dateOfBirth, created, modified,
                firstName, lastName, isEnabled, isCredentialsNonExpired, isAccountNonLocked, isAccountNonExpired);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
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
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

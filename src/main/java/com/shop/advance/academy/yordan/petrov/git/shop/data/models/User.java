package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.UserType;
import io.micrometer.core.lang.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class model for the User entity.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
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
    private Set<ContactInformation> contactInformation = new HashSet<>();
    private Collection<ShoppingCart> shoppingCart;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isAccountNonExpired = true;
    private Set<Role> authorities = new HashSet<>();

    /**
     * Constructor empty(for serialization purposes) for the user.
     */
    public User() {
    }

    /**
     * Method for
     *
     * @return
     */
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

    /**
     * Method for
     *
     * @return
     */
    @Override
    @NonNull
    @NotEmpty
    @Length(min = 8, max = 128, message = "password must be at least 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", unique = true, nullable = false)
    public String getPassword() {
        return this.password;
    }

    /**
     * Method for
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    public UserType getUserType() {
        return this.userType;
    }

    /**
     * Method for
     *
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreated() {
        return this.created;
    }

    /**
     * Method for
     *
     * @param created
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "modified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getModified() {
        return this.modified;
    }

    /**
     * Method for
     *
     * @param modified
     */
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Method for
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method for
     *
     * @return
     */
    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Method for
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method for
     *
     * @return
     */
    @OneToMany(targetEntity = ContactInformation.class
            , fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    @JoinTable(name = "user_contact_information",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_information_id", referencedColumnName = "id"))
    public Set<ContactInformation> getContactInformation() {
        return this.contactInformation;
    }

    /**
     * Method for
     *
     * @param contactInformation
     */
    public void setContactInformation(Set<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    /**
     * Method for
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    /**
     * Method for
     *
     * @param credentialsNonExpired
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    /**
     * Method for
     *
     * @param accountNonLocked
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    /**
     * Method for
     *
     * @param accountNonExpired
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    @ManyToMany(targetEntity = Role.class,
            fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    /**
     * Method for
     *
     * @param authorities
     */
    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    /**
     * Method for
     *
     * @return
     */
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REMOVE}
            , mappedBy = "user")
    public Collection<ShoppingCart> getShoppingCart() {
        return this.shoppingCart;
    }

    /**
     * Method for
     *
     * @param shoppingCart
     */
    public void setShoppingCart(Collection<ShoppingCart> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


    /**
     * Method for
     *
     * @param o
     * @return
     */
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

    /**
     * Method for
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, userType, dateOfBirth, created, modified,
                firstName, lastName, isEnabled, isCredentialsNonExpired, isAccountNonLocked, isAccountNonExpired);
    }

    /**
     * Method for
     *
     * @return
     */
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

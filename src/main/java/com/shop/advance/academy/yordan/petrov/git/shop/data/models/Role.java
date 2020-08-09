package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    private String authority;
    private Set<User> users = new HashSet<>();

    /**
     * Constructor
     */
    public Role() {
    }

    /**
     * Constructor
     */
    public Role(String authority) {
        this.authority = authority;
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    @Column(name = "authority",
            unique = true,
            nullable = false)
    @JoinColumn(name = "authority", referencedColumnName = "id")
    public String getAuthority() {
        return this.authority;
    }

    /**
     * Method for
     *
     * @param authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * Method for
     *
     * @return
     */
    @ManyToMany(targetEntity = User.class,
            mappedBy = "authorities",
            fetch = FetchType.EAGER
    )

    public Set<User> getUsers() {
        return this.users;
    }

    /**
     * Method for
     *
     * @param users
     */
    public void setUsers(Set<User> users) {
        this.users = users;
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
        if (!(o instanceof Role)) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return Objects.equals(authority, role.authority);
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authority);
    }

    /**
     * Method for
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("authority='").append(authority).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

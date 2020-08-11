package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class base model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@MappedSuperclass
public abstract class BaseEntity {

    Long id;

    /**
     * Constructor
     */
    public BaseEntity() {
    }

    /**
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false,
            unique = true,
            updatable = false)

    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

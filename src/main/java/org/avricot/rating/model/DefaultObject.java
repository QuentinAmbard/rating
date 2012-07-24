package org.avricot.rating.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.util.ObjectUtils;

@MappedSuperclass
public abstract class DefaultObject implements Serializable {
    private static final long serialVersionUID = -4505680552194377804L;

    @Column(name = "ID")
    @Id
    @GeneratedValue
    private Long id;

    public DefaultObject() {
    }

    public DefaultObject(final Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return 0;
        }
        return getId().intValue();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DefaultObject otherObj = (DefaultObject) obj;
        if (!getId().equals(otherObj.getId())) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if (getId() == null) {
            return "no Id, new object";
        }
        return ObjectUtils.nullSafeToString(getId());
    }
}

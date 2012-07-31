package org.avricot.rating.model.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;

@Entity
@Table(name = "FORK")
public class Fork extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUES")
    private String values;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(final String values) {
        this.values = values;
    }

}

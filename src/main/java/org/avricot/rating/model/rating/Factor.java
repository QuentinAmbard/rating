package org.avricot.rating.model.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.company.Sector;

@Entity
@Table(name = "FACTOR")
public class Factor extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SECTOR")
    private Sector sector;

    @Column(name = "ORDER")
    private Integer order;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(final Sector sector) {
        this.sector = sector;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(final Integer order) {
        this.order = order;
    }
}

package org.avricot.rating.model.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.company.EditionStep;
import org.avricot.rating.model.company.Sector;

@Entity
@Table(name = "RATING_TYPE")
public class RatingType extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "SECTOR")
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @Column(name = "STEP")
    @Enumerated(EnumType.STRING)
    private EditionStep step;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE_VALUE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    public EditionStep getStep() {
        return step;
    }

    public void setStep(final EditionStep step) {
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String[] getDropDownValues() {
        return defaultValue.split("\\|");
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(final Sector sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "RatingType [sector=" + sector + ", step=" + step + ", name=" + name + ", type=" + type + ", defaultValue=" + defaultValue + "]";
    }
}

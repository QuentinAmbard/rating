package org.avricot.rating.model.rating;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.company.Sector;

@Entity
@Table(name = "RATING_TYPE")
public class RatingType extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @ManyToMany
    @JoinTable(name = "RATING_TYPE_SECTOR", joinColumns = @JoinColumn(name = "RATING_TYPE_ID", referencedColumnName = "ID"), //
    inverseJoinColumns = @JoinColumn(name = "SECTOR_ID", referencedColumnName = "ID"))
    private Set<Sector> sectors;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ratingType")
    private Set<RatingTypeStep> steps;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE_VALUE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @Column(name = "VALIDATION")
    private String validation;

    @Column(name = "SUB_MENU")
    private String subMenu;

    @Column(name = "ORDER")
    private int order;

    @Column(name = "SUM")
    private String sum;

    @Transient
    private boolean hidden;

    @Column(name = "H_LEVEL")
    private String hLevel;

    @Column(name = "GLOBAL")
    private boolean global;

    public Set<RatingTypeStep> getSteps() {
        return steps;
    }

    public void setStep(final Set<RatingTypeStep> step) {
        this.steps = step;
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

    public Set<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(final Set<Sector> sector) {
        this.sectors = sector;
    }

    @Override
    public String toString() {
        return "RatingType [sector=" + sectors + ", step=" + steps + ", name=" + name + ", type=" + type + ", defaultValue=" + defaultValue + "]";
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(final String validation) {
        this.validation = validation;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(final String subMenu) {
        this.subMenu = subMenu;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(final String sum) {
        this.sum = sum;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }

    public String gethLevel() {
        return hLevel;
    }

    public void sethLevel(final String hLevel) {
        this.hLevel = hLevel;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(final boolean global) {
        this.global = global;
    }
}

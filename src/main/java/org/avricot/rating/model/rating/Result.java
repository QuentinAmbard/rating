package org.avricot.rating.model.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.company.Sector;

@Entity
@Table(name = "RESULT")
public class Result extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECTOR_ID")
    private Sector sector;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE_VALUE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "SUB_MENU")
    private String subMenu;

    @Column(name = "MENU")
    private String menu;

    @Column(name = "H_LEVEL")
    private String hLevel;

    @Column(name = "ORDER")
    private int order;

    @Column(name = "SUM")
    private String sum;

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

    public Sector getSector() {
        return sector;
    }

    public void setSector(final Sector sector) {
        this.sector = sector;
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

    public String gethLevel() {
        return hLevel;
    }

    public void sethLevel(final String hLevel) {
        this.hLevel = hLevel;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(final String menu) {
        this.menu = menu;
    }
}

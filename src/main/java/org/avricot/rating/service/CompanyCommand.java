package org.avricot.rating.service;

import java.util.Date;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.Sector;
import org.hibernate.validator.constraints.NotEmpty;

public class CompanyCommand {
    private Long id;
    private boolean next = true;
    @NotEmpty
    private String name;
    private String businessId;
    private Date creationDate;
    private Sector sector;
    private long dayNumber;
    private long yearNumber;

    public CompanyCommand() {

    }

    public CompanyCommand(final Company company) {
        id = company.getId();
        name = company.getName();
        businessId = company.getBusinessId();
        creationDate = company.getCreationDate();
        sector = company.getSector();
        dayNumber = company.getDayNumber();
        yearNumber = company.getYearNumber();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(final String businessId) {
        this.businessId = businessId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(final Sector sector) {
        this.sector = sector;
    }

    public long getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(final long dayNumber) {
        this.dayNumber = dayNumber;
    }

    public long getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(final long yearNumber) {
        this.yearNumber = yearNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }
}

package org.avricot.rating.service;

import java.util.Date;

import org.avricot.rating.model.company.Company;
import org.hibernate.validator.constraints.NotEmpty;

public class CompanyCommand {
    private Long id;
    private boolean next = true;
    @NotEmpty
    private String name;
    @NotEmpty
    private String annalistName;
    private String description;
    private String businessId;
    private Date creationDate;
    private Long sectorId;
    private int dayNumber;
    private int yearNumber;

    public CompanyCommand() {
        dayNumber = 365;
    }

    public CompanyCommand(final Company company) {
        id = company.getId();
        name = company.getName();
        description = company.getDescription();
        annalistName = company.getAnnalistName();
        businessId = company.getBusinessId();
        creationDate = company.getCreationDate();
        sectorId = company.getSector().getId();
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

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(final Long sectorId) {
        this.sectorId = sectorId;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(final int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(final int yearNumber) {
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

    public String getAnnalistName() {
        return annalistName;
    }

    public void setAnnalistName(final String annalistName) {
        this.annalistName = annalistName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}

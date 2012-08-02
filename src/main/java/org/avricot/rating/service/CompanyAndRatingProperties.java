package org.avricot.rating.service;

import java.util.Map;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.Step;
import org.avricot.rating.model.rating.RatingProperty;
import org.avricot.rating.model.rating.RatingType;

public class CompanyAndRatingProperties {
    private Company company;
    private Map<RatingType, RatingProperty> properties;
    private boolean displayYear;
    private Step step;

    public CompanyAndRatingProperties(final Company company, final Map<RatingType, RatingProperty> properties, final boolean displayYear, final Step step) {
        this.company = company;
        this.step = step;
        this.properties = properties;
        this.displayYear = displayYear;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public Map<RatingType, RatingProperty> getProperties() {
        return properties;
    }

    public void setProperties(final Map<RatingType, RatingProperty> properties) {
        this.properties = properties;
    }

    public boolean isDisplayYear() {
        return displayYear;
    }

    public void setDisplayYear(final boolean displayYear) {
        this.displayYear = displayYear;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(final Step step) {
        this.step = step;
    }
}

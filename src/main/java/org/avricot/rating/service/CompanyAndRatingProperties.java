package org.avricot.rating.service;

import java.util.Map;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.rating.RatingProperty;
import org.avricot.rating.model.rating.RatingType;

public class CompanyAndRatingProperties {
    private Company company;
    private Map<RatingType, RatingProperty> properties;

    public CompanyAndRatingProperties(final Company company, final Map<RatingType, RatingProperty> properties) {
        this.company = company;
        this.properties = properties;
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
}

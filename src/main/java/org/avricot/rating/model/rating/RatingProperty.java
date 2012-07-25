package org.avricot.rating.model.rating;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.EditionStep;

@Entity
@Table(name = "RATING_PROPERTY")
public class RatingProperty {
    private Company company;
    private String name;
    private String value;
    private EditionStep step;

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public EditionStep getStep() {
        return step;
    }

    public void setStep(final EditionStep step) {
        this.step = step;
    }
}

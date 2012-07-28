package org.avricot.rating.model.rating;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.company.Company;

@Entity
@Table(name = "RATING_PROPERTY")
public class RatingProperty extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @MapKey(name = "year")
    private Map<Integer, RatingPropertyValue> values = new HashMap<Integer, RatingPropertyValue>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RATING_TYPE_ID")
    private RatingType type;

    public RatingProperty() {

    }

    public RatingProperty(final Company company, final RatingType type) {
        this.company = company;
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public RatingType getType() {
        return type;
    }

    public void setType(final RatingType type) {
        this.type = type;
    }

    public Map<Integer, RatingPropertyValue> getValues() {
        return values;
    }

    public void setValues(final Map<Integer, RatingPropertyValue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "RatingProperty [company=" + company + ", type=" + type + "]";
    }

}

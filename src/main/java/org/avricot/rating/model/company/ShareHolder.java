package org.avricot.rating.model.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;

@Entity
@Table(name = "SHAREHOLDER")
public class ShareHolder extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "PERCENT")
    private Float percent;

    public ShareHolder(final Company company, final String firstname, final String lastname, final Float percent) {
        this.company = company;
        this.firstname = firstname;
        this.lastname = lastname;
        this.percent = percent;
    }

    public ShareHolder() {

    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(final Float percent) {
        this.percent = percent;
    }
}

package org.avricot.rating.model.company;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.rating.RatingProperty;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "COMPANY")
public class Company extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "BUSINESS_ID")
    private String businessId;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "LEADER_NAME")
    private String leaderName;

    @Column(name = "SECTOR")
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @OneToMany(mappedBy = "company")
    @MapKey(name = "name")
    private final HashMap<String, RatingProperty> properties = new HashMap<String, RatingProperty>();

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

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(final String leaderName) {
        this.leaderName = leaderName;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(final Sector sector) {
        this.sector = sector;
    }

    public HashMap<String, RatingProperty> getProperties() {
        return properties;
    }
}

package org.avricot.rating.model.company;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.rating.RatingProperty;
import org.avricot.rating.model.rating.RatingType;
import org.avricot.rating.model.user.User;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "COMPANY")
public class Company extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "DAY_NUMBER")
    private long dayNumber = 365;

    @Column(name = "YEAR_NUMBER")
    private long yearNumber = 3;

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
    @MapKey(name = "type")
    private Map<RatingType, RatingProperty> properties = new HashMap<RatingType, RatingProperty>();

    @Column(name = "SCORE")
    private Float score;

    public Company() {

    }

    public Company(final User user) {
        this.user = user;
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

    public Map<RatingType, RatingProperty> getProperties() {
        return properties;
    }

    public void setProperties(final HashMap<RatingType, RatingProperty> properties) {
        this.properties = properties;
    }

    public void setProperties(final Map<RatingType, RatingProperty> properties) {
        this.properties = properties;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
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

    public Float getScore() {
        return score;
    }

    public void setScore(final Float score) {
        this.score = score;
    }

}

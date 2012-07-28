package org.avricot.rating.model.rating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "RATING_PROPERTY_VALUE")
public class RatingPropertyValue extends DefaultObject {
    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = LoggerFactory.getLogger(RatingPropertyValue.class);

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RATING_PROPERTY_ID")
    private RatingProperty property;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "YEAR")
    private Integer year;

    public RatingPropertyValue() {
    }

    public RatingPropertyValue(final RatingProperty property, final Integer year) {
        this.property = property;
        this.year = year;
    }

    public RatingProperty getProperty() {
        return property;
    }

    public void setProperty(final RatingProperty property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "RatingPropertyValue [property=" + property + ", value=" + value + ", year=" + year + "]";
    }

    public Integer getint() {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            LOGGER.error("can't convert to int {}", value);
            throw e;
        }
    }

    public Date getDate() {
        try {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(value);
        } catch (ParseException e) {
            LOGGER.error("can't convert to date {}", value);
            throw new IllegalStateException("can't convert to date " + value, e);
        }
    }
}
package org.avricot.rating.model.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.company.Step;

@Entity
@Table(name = "RATING_TYPE_STEP")
public class RatingTypeStep extends DefaultObject {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "STEP_ID")
    private Step step;

    @ManyToOne
    @JoinColumn(name = "RATING_TYPE_ID")
    private RatingType ratingType;

    @Column(name = "HIDE")
    private boolean hidden;

    public Step getStep() {
        return step;
    }

    public void setStep(final Step step) {
        this.step = step;
    }

    public RatingType getRatingType() {
        return ratingType;
    }

    public void setRatingType(final RatingType ratingType) {
        this.ratingType = ratingType;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
}

package org.avricot.rating.model.company;

public enum EditionStep {
    STEP_1, STEP_2, STEP_3;

    public static EditionStep getByOrdinal(final int i) {
        for (EditionStep es : EditionStep.values()) {
            if (es.ordinal() == i) {
                return es;
            }
        }
        return null;
    }

    public EditionStep getNext() {
        return getByOrdinal(ordinal() + 1);
    }
}

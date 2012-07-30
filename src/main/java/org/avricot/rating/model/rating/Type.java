package org.avricot.rating.model.rating;

public enum Type {
    TIME, PERCENT, NUM, DROP_DOWN, RADIO;

    public String getName() {
        return name();
    }
}

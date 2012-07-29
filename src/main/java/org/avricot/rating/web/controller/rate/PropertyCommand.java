package org.avricot.rating.web.controller.rate;

import java.util.HashMap;
import java.util.Map;

public class PropertyCommand {
    private Map<String, Map<Integer, String>> properties = new HashMap<String, Map<Integer, String>>();
    private boolean next = true;

    public Map<String, Map<Integer, String>> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, Map<Integer, String>> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "PropertyCommand [properties=" + properties + "]";
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }
}

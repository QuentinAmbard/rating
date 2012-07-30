package org.avricot.rating.web.controller.rate;

import java.util.HashMap;
import java.util.Map;

public class PropertyCommand {
    private Map<String, MapProperties> properties = new HashMap<String, MapProperties>();

    private boolean next = true;

    public Map<String, MapProperties> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, MapProperties> properties) {
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

    public static class MapProperties extends HashMap<Integer, String> {
        private static final long serialVersionUID = 1L;

        private Integer globalValue;

        public Integer getGlobalValue() {
            return globalValue;
        }

        public void setGlobalValue(final Integer globalValue) {
            this.globalValue = globalValue;
        }
    }

}

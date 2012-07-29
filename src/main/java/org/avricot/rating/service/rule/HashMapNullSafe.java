package org.avricot.rating.service.rule;

import java.util.HashMap;

/**
 * Return never-null value (0 if null).
 */
public class HashMapNullSafe extends HashMap<Integer, Float> {
    private static final long serialVersionUID = 1L;

    @Override
    public Float get(final Object key) {
        Float value = super.get(key);
        if (value == null) {
            return 0F;
        }
        return value;
    }
}

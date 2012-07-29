package org.avricot.rating.service.rule;

import java.util.HashMap;

/**
 * Return never-null value (0 if null).
 */
public class HashMapNullSafe extends HashMap<Integer, Integer> {
    private static final long serialVersionUID = 1L;

    @Override
    public Integer get(final Object key) {
        Integer value = super.get(key);
        if (value == null) {
            return 0;
        }
        return value;
    }
}

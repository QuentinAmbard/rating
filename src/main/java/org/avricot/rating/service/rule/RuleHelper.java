package org.avricot.rating.service.rule;

import java.util.Map;

public class RuleHelper {
    public static Calc get(final Map<String, Calc> prop, final String name) {
        return prop.get(name);
    }

}

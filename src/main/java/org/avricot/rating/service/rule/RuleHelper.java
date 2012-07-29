package org.avricot.rating.service.rule;

import java.util.Map;

public class RuleHelper {
    public static Calc get(final Map<String, Calc> prop, final String name) {
        Calc calc = prop.get(name);
        if (calc == null) {
            return new Calc();
        }
        return calc;
    }

}

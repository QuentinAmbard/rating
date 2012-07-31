package org.avricot.rating.service.rule;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.avricot.rating.repository.fork.ForkRepository;
import org.springframework.stereotype.Component;

@Component
public class RuleHelper {
    public static Calc get(final Map<String, Calc> prop, final String name) {
        Calc calc = prop.get(name);
        if (calc == null) {
            return new Calc();
        }
        return calc;
    }

    private final ForkRepository forkRepository;
    private final Map<String, Float[]> values = new HashMap<String, Float[]>();

    @Inject
    public RuleHelper(final ForkRepository source) {
        this.forkRepository = source;
    }

    public synchronized int getForkPercent(final String name, final Float value) {
        float v = getFork(name, value);
        Float[] vals = values.get(name);
        return (int) (v / (vals.length) * 100);
    }


    public synchronized Float getIndice(final String name, final Number value) {
        ensureKeyExists(name);
        Float[] vals = values.get(name);
        return vals[value.intValue()];
    }

    public synchronized int getFork(final String name, final Float value) {
        ensureKeyExists(name);
        Float[] vals = values.get(name);
        List<Float> list;
        boolean decroi = vals[0] > vals[vals.length - 1];
        Integer result = null;
        if (decroi) {
            list = Arrays.asList(Arrays.copyOf(vals, vals.length));
            Collections.reverse(list);
        } else {
            list = Arrays.asList(vals);
        }
        if (value < list.get(0)) {
            result = 0;
        } else if (value >= list.get(vals.length - 1)) {
            result = vals.length;
        } else {
            for (int i = 0; i < vals.length - 1; i++) {
                if (value >= list.get(i) && (i + 1 > vals.length || value < list.get(i + 1))) {
                    result = i + 1;
                    break;
                }
            }
        }
        if (result == null) {
            result = vals.length - 1;
        }
        if (decroi) {
            result = vals.length - result;
        }
        return result;
    }

    private void ensureKeyExists(final String name) {
        if (!values.containsKey(name)) {
            String vs = forkRepository.findByName(name).getValues();
            String[] strValues = vs.split("\\|");
            Float[] val = new Float[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                val[i] = Float.valueOf(strValues[i]);
            }
            values.put(name, val);
        }
    }
}

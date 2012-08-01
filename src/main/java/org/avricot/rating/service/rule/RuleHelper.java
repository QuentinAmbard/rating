package org.avricot.rating.service.rule;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.avricot.rating.model.company.Fork;
import org.avricot.rating.repository.fork.ForkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RuleHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(RuleHelper.class);

    public static Float getGlobalValue(final Map<String, Float> prop, final String name) {
        Float val = prop.get(name);
        if (val == null) {
            LOGGER.info("can't find global value with name {}", name);
            return 0F;
        }
        return val;
    }

    public static Calc get(final Map<String, Calc> prop, final String name) {
        Calc calc = prop.get(name);
        if (calc == null) {
            LOGGER.info("can't find calc with name {}", name);
            return new Calc();
        }
        return calc;
    }

    public static Float getF(final Map<String, Float> prop, final String name) {
        Float val = prop.get(name);
        if (val == null) {
            LOGGER.info("can't find float value with name {}", name);
            return 0F;
        }
        return val;
    }

    private final ForkRepository forkRepository;
    private final Map<String, Float[]> values = new HashMap<String, Float[]>();

    @Inject
    public RuleHelper(final ForkRepository source) {
        this.forkRepository = source;
    }

    public synchronized Float getForkPercent(final String name, final Object val) {
        Float value = (Float) val;
        float v = getFork(name, value);
        Float[] vals = values.get(name);
        return v / (vals.length) * 100F;
    }

    public synchronized Float getIndice(final String name, final Object val) {
        Number value = (Number) val;
        ensureKeyExists(name);
        Float[] vals = values.get(name);
        return vals[value.intValue()];
    }

    public synchronized Float getFork(final String name, final Object val) {
        Float value = (Float) val;
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
        return new Float(result);
    }

    private void ensureKeyExists(final String name) {
        if (!values.containsKey(name)) {
            Fork fork = forkRepository.findByName(name);
            if (fork == null) {
                LOGGER.error("can't find fork with name {} in the db", name);
            }
            String vs = fork.getValues();
            String[] strValues = vs.split("\\|");
            Float[] val = new Float[strValues.length];
            for (int i = 0; i < strValues.length; i++) {
                val[i] = Float.valueOf(strValues[i]);
            }
            values.put(name, val);
        }
    }
}

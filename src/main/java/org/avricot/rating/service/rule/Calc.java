package org.avricot.rating.service.rule;

import java.util.Map;
import java.util.Map.Entry;

public class Calc {
    private Map<Integer, Integer> values = new HashMapNullSafe();

    public Calc div(final int value) {
        return mult(1 / value);
    }

    public Calc div(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Integer> e : values.entrySet()) {
            int value;
            if (c.getValues().get(e.getKey()) == 0L) {
                value = 0;
            } else {
                value = e.getValue() / c.getValues().get(e.getKey());
            }
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Calc mult(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Integer> e : values.entrySet()) {
            int value = e.getValue() / c.getValues().get(e.getKey());
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Calc mult(final int value) {
        Calc result = new Calc();
        for (Entry<Integer, Integer> e : values.entrySet()) {
            result.getValues().put(e.getKey(), e.getValue() * value);
        }
        return result;
    }

    public Calc sub(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Integer> e : values.entrySet()) {
            int value = e.getValue() - c.getValues().get(e.getKey());
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Calc plus(final int value) {
        Calc result = new Calc();
        for (Entry<Integer, Integer> e : values.entrySet()) {
            result.getValues().put(e.getKey(), e.getValue() + value);
        }
        return result;
    }

    public Calc minus(final int value) {
        return plus(-value);
    }

    public Calc plus(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Integer> e : values.entrySet()) {
            int value = e.getValue() + c.getValues().get(e.getKey());
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Map<Integer, Integer> getValues() {
        return values;
    }

    public void setValues(final Map<Integer, Integer> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Calc [values=" + values + "]";
    }
}
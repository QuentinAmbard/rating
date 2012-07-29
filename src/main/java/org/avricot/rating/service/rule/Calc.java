package org.avricot.rating.service.rule;

import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

public class Calc {
    private Map<Integer, Float> values = new HashMapNullSafe();

    public Calc div(final int value) {
        return mult(1 / value);
    }

    public Calc div(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            Float value;
            if (c.getValues().get(e.getKey()) == 0L) {
                value = 0F;
            } else {
                value = e.getValue() / c.getValues().get(e.getKey());
            }
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Calc mult(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            Float value = e.getValue() / c.getValues().get(e.getKey());
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Calc mult(final int value) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            result.getValues().put(e.getKey(), e.getValue() * value);
        }
        return result;
    }

    public Calc sub(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            Float value = e.getValue() - c.getValues().get(e.getKey());
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Calc plus(final int value) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            result.getValues().put(e.getKey(), e.getValue() + value);
        }
        return result;
    }

    public Calc minus(final int value) {
        return plus(-value);
    }

    public Calc minus(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            Float value = e.getValue() - c.getValues().get(e.getKey());
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Calc plus(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            Float value = e.getValue() + c.getValues().get(e.getKey());
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    public Float avg(final Integer yearsNumber) {
        Float result = 0F;
        Integer currentYear = getCurrentYear();
        for (int i = currentYear; i > currentYear - yearsNumber; i--) {
            result += values.get(i);
        }
        return result / yearsNumber;
    }

    public Calc growth(final Calc c) {
        Calc result = new Calc();
        for (Entry<Integer, Float> e : values.entrySet()) {
            Float previousValue = c.getValues().get(e.getKey() - 1);
            Float value;
            if (previousValue == 0F) {
                value = 0F;
            } else {
                value = e.getValue() / previousValue;
            }
            result.getValues().put(e.getKey(), value);
        }
        return result;
    }

    private Integer getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public Map<Integer, Float> getValues() {
        return values;
    }

    public void setValues(final Map<Integer, Float> values) {
        this.values = values;
    }

    public Float getYear(final Integer year) {
        return values.get(year);
    }

    @Override
    public String toString() {
        return "Calc [values=" + values + "]";
    }
}
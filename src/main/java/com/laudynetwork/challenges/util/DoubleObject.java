package com.laudynetwork.challenges.util;

public class DoubleObject {
    public double value;
    public double max = 1;
    public double min = 0;
    public double stepSize = 1;

    public DoubleObject(double i) {
        this.value = i;
    }

    public DoubleObject(double i, double min, double max) {
        this.value = i;
        this.max = max;
        this.min = min;
    }

    public DoubleObject(double i, double stepSize) {
        this.value = i;
        this.stepSize = stepSize;
    }

    public DoubleObject(double i, double min, double max, double stepSize) {
        this.value = i;
        this.max = max;
        this.min = min;
        this.stepSize = stepSize;
    }

    public boolean isTrue() {
        if (value == 0) {
            return false;
        } else {
            return true;
        }
    }

    public double getValue() {
        return value;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getStepSize() {
        return stepSize;
    }


    public int getIntValue() {
        return (int) Math.round(value);
    }

    public int getIntMax() {
        return (int) Math.round(max);
    }

    public int getIntMin() {
        return (int) Math.round(min);
    }

    public int getIntStepSize() {
        return (int) Math.round(stepSize);
    }


    public String getString() {
        return "value: " + value + ", max: " + max + ", min: " + min + ", step: " + stepSize;
    }

    public String getString(double var) {
        return var + "";
    }

    public boolean isFalse() {
        return !isTrue();
    }
}

package com.laudynetwork.challenges.util;

public class IntObjekt {
    public int value;
    public int max = 1;
    public int min = 0;

    public IntObjekt(int i) {
        this.value = i;
    }

    public IntObjekt(int i, int min, int max) {
        this.value = i;
        this.max = max;
        this.min = min;
    }

    public boolean isTrue() {
        if (value == 0) {
            return false;
        } else {
            return true;
        }
    }
}

package com.terikon.cordova.photolibrary;

/**
 * Created by hxg on 9/03/18.
 */

public enum ResponsiveSize {
    LARGE(3000),MEDIUM(2000),SMALL(1000);
    final int from;
    ResponsiveSize(int from) {
        this.from = from;
    }

    static ResponsiveSize find(int shorter) {
        if(isBetween(shorter, 0, SMALL.from)) {
            return SMALL;
        }
        if(isBetween(shorter, SMALL.from, MEDIUM.from)) {
            return MEDIUM;
        }
        return LARGE;
    }

    static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}

package com.terikon.cordova.photolibrary;

import android.util.Log;

/**
 * Created by gota on 8/03/18.
 */

public class WatermarkConfig {
    private static final String LOG_TAG = "WatermarkConfig";

    final Margin topLeftLine1;
    final Margin topLeftLine2;
    final Margin topLeftLine3;
    final Margin topRightLine1;
    final Margin bottomLeftLine1;
    final Margin bottomLeftLine2;
    final Margin bottomRightLine1;
    final Margin bottomRightLine2;
    final int textSize;
    static int toInt(double val) {
        return Double.valueOf(val).intValue();
    }

    public WatermarkConfig(Photo photo) {
       textSize = photo.textSize();
       Log.d(LOG_TAG, "textSize: " + textSize);

       final double lineMultiply = photo.lineMultiply();
       final int baseMargin = photo.baseMargin();
       final int leftX = baseMargin;
       final int rightX = photo.rightX();
       final int topLin2Y = photo.topLin2Y();
       final int topLin3Y = photo.topLin3Y();

       final int bottomLine1Y = photo.bottomLine1Y();

       final int bottomRightLine2Y = -toInt(baseMargin + (textSize * (lineMultiply + 0.2)));

       topLeftLine1 = new Margin(leftX, baseMargin);
       topLeftLine2 = new Margin(leftX, topLin2Y);
       topLeftLine3 = new Margin(leftX, topLin3Y);

       bottomLeftLine1 = new Margin(leftX, bottomLine1Y);
       bottomLeftLine2 = photo.bottomLeftLine2();

       topRightLine1 = new Margin(rightX, baseMargin);
       bottomRightLine1 = new Margin(rightX, bottomLine1Y);
       bottomRightLine2 = new Margin(rightX, bottomRightLine2Y);
    }

    @Override
    public String toString() {
        return "WatermarkConfig{" +
                ", topLeftLine1=" + topLeftLine1 +
                ", topLeftLine2=" + topLeftLine2 +
                ", topLeftLine3=" + topLeftLine3 +
                ", topRightLine1=" + topRightLine1 +
                ", bottomLeftLine1=" + bottomLeftLine1 +
                ", bottomLeftLine2=" + bottomLeftLine2 +
                ", bottomRightLine1=" + bottomRightLine1 +
                ", textSize=" + textSize +

                '}';
    }
}

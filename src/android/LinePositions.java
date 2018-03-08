package com.terikon.cordova.photolibrary;

/**
 * Created by gota on 8/03/18.
 */

public class LinePositions {
    //final Size size;

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

    public LinePositions() {
       //this.size = size;

       textSize = 90;
       final double lineMultiply = 1.2;
       final int baseMargin = 30;
       final int leftX = baseMargin;
       final int rightX = -(baseMargin * 3);
       final int topLin2Y = toInt(baseMargin + (textSize * lineMultiply));
       final int topLin3Y = toInt(baseMargin + (textSize * lineMultiply * 2));

       final int bottomLine1Y = -(baseMargin);
       final int bottomLine2Y = -toInt(baseMargin + (textSize * lineMultiply));

       final int bottomRightLine2Y = -toInt(baseMargin + (textSize * (lineMultiply + 0.2)));

       topLeftLine1 = new Margin(leftX, baseMargin);
       topLeftLine2 = new Margin(leftX, topLin2Y);
       topLeftLine3 = new Margin(leftX, topLin3Y);

       bottomLeftLine1 = new Margin(toInt(leftX * 1.5), bottomLine1Y);
       bottomLeftLine2 = new Margin(leftX, bottomLine2Y);

       topRightLine1 = new Margin(rightX, baseMargin);
       bottomRightLine1 = new Margin(rightX, bottomLine1Y);
       bottomRightLine2 = new Margin(rightX, bottomRightLine2Y);
    }

    @Override
    public String toString() {
        return "LinePositions{" +
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

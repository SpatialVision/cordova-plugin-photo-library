package com.terikon.cordova.photolibrary;

/**
 * Created by gota on 8/03/18.
 */

public class Position {
    final int width;
    final int height;

    final Margin topLeftLine1;
    final Margin topLeftLine2;
    final Margin topLeftLine3;
    final Margin topRightLine1;
    final Margin bottomLeftLine1;
    final Margin bottomLeftLine2;
    final Margin bottomRightLine1;
    final Margin bottomRightLine2;
    final int textSize;
    final int area;

    public Position(int width, int height) {
       this.width = width;
       this.height = height;

       area = width * height;
       textSize = 90;
       final double lineMultiply = 1.4;
       final int baseMargin = 30;
       final int leftX = baseMargin;
       final int rightX = -(baseMargin * 3);
       final int topLin2Y = Double.valueOf(baseMargin + (textSize * lineMultiply)).intValue();
       final int topLin3Y = Double.valueOf(baseMargin + (textSize * lineMultiply * 2)).intValue();

       final int bottomLine1Y = -(baseMargin);
       final int bottomLine2Y = -Double.valueOf(baseMargin + (textSize * lineMultiply)).intValue();

       topLeftLine1 = new Margin(leftX, baseMargin);
       topLeftLine2 = new Margin(leftX, topLin2Y);
       topLeftLine3 = new Margin(leftX, topLin3Y);

       topRightLine1 = new Margin(rightX, baseMargin);

       bottomLeftLine1 = new Margin(leftX, bottomLine1Y);
       bottomLeftLine2 = new Margin(leftX, bottomLine2Y);

       bottomRightLine1 = new Margin(rightX, bottomLine1Y);
       bottomRightLine2 = new Margin(rightX, bottomLine2Y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "width=" + width +
                ", height=" + height +
                ", topLeftLine1=" + topLeftLine1 +
                ", topLeftLine2=" + topLeftLine2 +
                ", topLeftLine3=" + topLeftLine3 +
                ", topRightLine1=" + topRightLine1 +
                ", bottomLeftLine1=" + bottomLeftLine1 +
                ", bottomLeftLine2=" + bottomLeftLine2 +
                ", bottomRightLine1=" + bottomRightLine1 +
                ", textSize=" + textSize +
                ", area=" + area +
                '}';
    }
}

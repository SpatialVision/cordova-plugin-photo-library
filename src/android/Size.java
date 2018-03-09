package com.terikon.cordova.photolibrary;

/**
 * Created by gota on 8/03/18.
 */
public class Size {
    final int width;
    final int height;

    public Size(Size another) {
        this(another.width, another.height);
    }

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    Size multiply(double widthBy, double heightBy) {
        return new Size(Double.valueOf(this.width * widthBy).intValue(),
                        Double.valueOf(this.height * heightBy).intValue());
    }

    boolean isPortrait() {
        return width < height;
    }

    int shorter() {
        return isPortrait()? width: height;
    }

    ResponsiveSize responsiveSize() {
        return ResponsiveSize.find(shorter());
    }

    int area() {
      return width * height;
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

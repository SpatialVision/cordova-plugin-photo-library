package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;
import android.graphics.Rect;

import org.apache.cordova.camera.ExifHelper;

/**
 * Created by gota on 8/03/18.
 */

public class Photo {
    final Bitmap src;
    final Size size;
    final ExifHelper exif;

    public Photo(Bitmap src, ExifHelper exif) {
        this.src = src;
        this.exif = exif;
        size = new Size(src.getWidth(), src.getHeight());
    }

    Rect header() {
      Size headerSize = new Size(size).multiply(1, 0.09);
      return new Rect(0, 0, size.width, headerSize.height);
    }

    Rect footer() {
      Size  footerSize = new Size(size).multiply(1, 0.07);
      return new Rect(0, (size.height - footerSize.height), size.width, size.height);
    }


    int textSize() {
        return size.shorter() / 100 * 3;
    }

    int baseMargin() { return 30; }

    int topLin2Y() {
        switch (size.responsiveSize()) {
            case LARGE:
                return toInt((textSize() * lineMultiply()));
            case MEDIUM:
            case SMALL:
            default:
                return toInt((textSize() * 1.5));
        }
    }

    int topLin3Y() {
        switch (size.responsiveSize()) {
            case LARGE:
                return toInt((textSize() * lineMultiply() * 2));
            case MEDIUM:
            case SMALL:
            default:
                return toInt((textSize() * 1.5 * 1.6));
        }

    }


    int rightX() {
        switch (size.responsiveSize()) {
            case LARGE:
                return -(baseMargin() * 3);
            case MEDIUM:
            case SMALL:
            default:
                return -toInt(baseMargin() * 1.5);
        }
    }

    double lineMultiply() {
        switch (size.responsiveSize()) {
            case LARGE:
                return 1.2;
            case MEDIUM:
            case SMALL:
            default:
                return 1.1;
        }
    }

    static int toInt(double val) {
        return Double.valueOf(val).intValue();
    }

}

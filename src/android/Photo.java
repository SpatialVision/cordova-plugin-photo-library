package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;

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

}

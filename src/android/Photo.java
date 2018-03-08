package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;

/**
 * Created by gota on 8/03/18.
 */

public class Photo {
    final Bitmap src;
    final Size size;

    public Photo(Bitmap src) {
        this.src = src;
        size = new Size(src.getWidth(), src.getHeight());
    }

}

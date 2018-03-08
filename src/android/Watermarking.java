package com.terikon.cordova.photolibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by hxg on 7/03/18.
 */

public class Watermarking {
    private static final String LOG_TAG = "Watermarking";

    public Watermarking(Context context, String url, WatermarkLines details) {
        Log.d(LOG_TAG, "url: " + url + ", " + details);
        boolean success = watermark(context, url, details);
        Log.d(LOG_TAG, "success: " + success);
    }

    boolean watermark(Context context, String photoUrl, WatermarkLines details) {
        Log.d(LOG_TAG, "watermark photoUrl: " + photoUrl);
        Photo photo = new ReadPhoto(photoUrl).read();
        Photo created  = new Watermarker(context, photo, details).mark();
        return new SavePhoto(photoUrl).save(created);
    }

}

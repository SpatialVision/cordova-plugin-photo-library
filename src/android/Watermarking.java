package com.terikon.cordova.photolibrary;
import android.util.Log;
/**
 * Created by hxg on 7/03/18.
 */

public class Watermarking {
    private static final String LOG_TAG = "Watermarking";


    public Watermarking(String url) {
        Log.d(LOG_TAG, "Watermarking url: " + url);

        boolean success = watermark(url);

        Log.d(LOG_TAG, "Watermarking success: " + success);



    }

    boolean watermark(String url) {
        return true;


    }
}

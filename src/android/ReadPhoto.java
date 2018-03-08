package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadPhoto {
    private static final String LOG_TAG = "Watermarking-ReadPhoto";
    final String url;

    ReadPhoto(String url) {
        this.url = url;
    }

    Bitmap read() {
        Log.d(LOG_TAG, "read file from url: " + url);
        Bitmap read = null;
        InputStream is = null;
        try {
            //return BitmapFactory.decodeFile(url);
            is = new URL(url).openStream();
            read = BitmapFactory.decodeStream( is );
            if(read == null) {
                throw new RuntimeException("Unable to create bitmap from url");
            }
            return read;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return read;
        }
    }
}

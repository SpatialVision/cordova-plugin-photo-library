package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

//import org.apache.cordova.camera.*;

import org.apache.cordova.camera.ExifHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadPhoto {
    private static final String LOG_TAG = "Watermarking-ReadPhoto";
    final String url;

    /**
     * url should be similar to
     * file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1520400932073.jpg
     * @param url
     */
    ReadPhoto(String url) {
        this.url = url;
    }

    Photo read() {
        Log.d(LOG_TAG, "read file from url: " + url);
        String filePath = url.replace("file://", "");
        ExifHelper exif = new ExifHelper();
        Bitmap read = null;
        InputStream is = null;
        try {
            exif.createInFile(filePath);
            exif.readExifData();
            //rotate = exif.getOrientation();
            //read = BitmapFactory.decodeFile(url);

            is = new URL(url).openStream();
            read = BitmapFactory.decodeStream( is );

            if(read == null) {
                throw new RuntimeException("Unable to create bitmap from url");
            }

            Log.d(LOG_TAG, "read: " + read + " dencity: " + read.getDensity() +
                    ", getWidth: " + read.getWidth() +
                    ", getHeight: " + read.getHeight() +
                    ", getScaledWidth(read.getDensity()): " + read.getScaledWidth(read.getDensity()) +
                    ", getScaledHeight(read.getDensity()): " + read.getScaledHeight(read.getDensity())
            );
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
            return new Photo(read, exif);
        }
    }
}

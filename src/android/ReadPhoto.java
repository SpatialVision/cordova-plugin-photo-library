package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

//import org.apache.cordova.camera.*;

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

    Bitmap read() {
        Log.d(LOG_TAG, "read file from url: " + url);
        Bitmap read = null;
        InputStream is = null;
        try {
            String filePath = url.replace("file://", "");
            //ExifHelper exifData = new ExifHelper();
            //exifData.createInFile(filePath);

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

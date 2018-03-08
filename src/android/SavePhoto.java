package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gota on 8/03/18.
 */

public class SavePhoto {
    private static final String LOG_TAG = "Watermarking-SavePhoto";
    final String url;

    SavePhoto(String url) {
        this.url = url;
    }

    boolean save(Photo toSave) {
        Log.d(LOG_TAG, "save url: " + url);
        String filePath = url.substring(7);
        File file = new File(filePath);
        OutputStream os = null;
        boolean success = false;
        try {
            Log.d(LOG_TAG, "save to file: " + filePath);
            os = new BufferedOutputStream(new FileOutputStream(file));
            success = toSave.src.compress(Bitmap.CompressFormat.JPEG, 50, os);

            toSave.exif.createOutFile(filePath);
            toSave.exif.writeExifData();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return success;
        }

    }
}

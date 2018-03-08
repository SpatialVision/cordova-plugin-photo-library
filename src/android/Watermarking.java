package com.terikon.cordova.photolibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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
        //file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1520400932073.jpg
        //Log.d(LOG_TAG, "Watermarking filePath: " + filePath);
        Log.d(LOG_TAG, "watermark url: " + url);

        Bitmap bMap = new ReadPhoto(url).read();
        Log.d(LOG_TAG, "watermark bMap: " + bMap);
//        InputStream is = null;
//
//        try {
//            is = new URL( url ).openStream();
//            bMap = BitmapFactory.decodeStream( is );
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                Log.e("Exception", "Watermarking File read failed: " + e.toString());
//                return false;
//            }
//        }

        String watermark = "Test watermark";
        Point location = new Point(10, 100);
        int alpha = 50;
        int size = 200;

        Bitmap created = mark(bMap, watermark, location, alpha, size);

        return new SavePhoto(url).save(created);
//        String filePath = url.substring(7);
//        File file = new File(filePath);
//        OutputStream os = null;
//        try {
//            Log.d(LOG_TAG, "Watermarking mark writing back to file");
//
//            os = new BufferedOutputStream(new FileOutputStream(file));
//
//            boolean compressed = created.compress(Bitmap.CompressFormat.JPEG, 50, os);
//
//            Log.d(LOG_TAG, "Watermarking mark compressed: " + compressed);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (os != null) {
//                    os.close();
//                }
//                Log.d(LOG_TAG, "Watermarking mark close file");
//            } catch (IOException e) {
//                Log.e("Exception", "Watermarking File write failed: " + e.toString());
//                return false;
//            }
//        }

//        return true;
    }

    public static Bitmap mark(Bitmap src, String watermark, Point location,
                              int alpha, int size) {
        Log.d(LOG_TAG, "Watermarking mark src: " + src );
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //paint.setAlpha(alpha);
        paint.setTextSize(size);
        //paint.setAntiAlias(true);
        paint.setUnderlineText(false);
        canvas.drawText(watermark, location.x, location.y, paint);

        return result;
    }
}

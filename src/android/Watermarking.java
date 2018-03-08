package com.terikon.cordova.photolibrary;

import android.content.Context;
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

    public Watermarking(Context context, String url, JobDetails details) {
        Log.d(LOG_TAG, "url: " + url + ", " + details);

        boolean success = watermark(context, url, details);

        Log.d(LOG_TAG, "success: " + success);
    }

    boolean watermark(Context context, String url, JobDetails details) {
        Log.d(LOG_TAG, "watermark url: " + url);

        Bitmap toWatermark = new ReadPhoto(url).read();

        Bitmap created  = new Watermarker(context, toWatermark, details).mark();

//        Log.d(LOG_TAG, "watermark toWatermark: " + toWatermark);
//        String watermark = "Test watermark";
//        Point location = new Point(10, 100);
//        int alpha = 50;
//        int size = 200;
//
//        Bitmap created = mark(toWatermark, watermark, location, alpha, size);

        return new SavePhoto(url).save(created);
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

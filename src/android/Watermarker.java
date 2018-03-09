package com.terikon.cordova.photolibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.vinaygaba.rubberstamp.RubberStamp;
import com.vinaygaba.rubberstamp.RubberStampConfig;
import com.vinaygaba.rubberstamp.RubberStampPosition;

/**
 * Created by gota on 8/03/18.
 */

public class Watermarker {
    private static final String LOG_TAG = "Watermarker";
    final Photo photo;
    final Rect header;
    final Rect footer;
    final WatermarkLines lines;
    final Context context;
    final WatermarkConfig config;
    private Bitmap marking;

    public Watermarker(Context context, Photo photo, WatermarkLines lines) {
        Log.d(LOG_TAG, "photo: " + photo);
        this.photo = photo;

        marking = photo.src;

        header = photo.header();
        footer = photo.footer();

        Log.d(LOG_TAG, "header: " + header);
        Log.d(LOG_TAG, "footer: " + footer);

        config = new WatermarkConfig(photo);

        this.context = context;
        this.lines = lines;
    }

    Photo mark() {
        return new Photo(
                markHeaderFooter(marking)
                //top left
                .mark(marking, lines.id, RubberStampPosition.TOP_LEFT, config.topLeftLine1)
                .mark(marking, lines.address, RubberStampPosition.TOP_LEFT, config.topLeftLine2)
                .mark(marking, lines.name, RubberStampPosition.TOP_LEFT, config.topLeftLine3)
                //top right
                .mark(marking, lines.name, RubberStampPosition.TOP_RIGHT, config.topRightLine1)
                //bottom left
                .mark(marking, lines.lat, RubberStampPosition.BOTTOM_LEFT, config.bottomLeftLine2)
                .mark(marking, lines.lng, RubberStampPosition.BOTTOM_LEFT, config.bottomLeftLine1)
                //bottom right
                .mark(marking, lines.date, RubberStampPosition.BOTTOM_RIGHT, config.bottomRightLine2)
                .mark(marking, lines.time, RubberStampPosition.BOTTOM_RIGHT, config.bottomRightLine1)
                .marking, photo.exif);
    }

    private Watermarker mark(Bitmap base, String watermark, RubberStampPosition position, Margin margin) {
        RubberStampConfig config = new RubberStampConfig.RubberStampConfigBuilder()
                .base(base)
                .rubberStamp(watermark)
                .rubberStampPosition(position)
                .alpha(255)
                .margin(margin.x, margin.y)
                .textColor(Color.WHITE)
                .textSize(this.config.textSize)
                .build();

        RubberStamp rubberStamp = new RubberStamp(context);

        return updateMarking(rubberStamp.addStamp(config));
    }

    private Watermarker markHeaderFooter(Bitmap src) {
        Log.d(LOG_TAG, "markHeaderFooter: " + src );
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(120);
        canvas.drawRect(header, paint);
        canvas.drawRect(footer, paint);

        return updateMarking(result);
    }

    private Watermarker updateMarking(Bitmap toUpdate) {
        marking = toUpdate;
        return this;
    }
}


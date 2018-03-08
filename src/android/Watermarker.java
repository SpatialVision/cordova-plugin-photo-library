package com.terikon.cordova.photolibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.vinaygaba.rubberstamp.RubberStamp;
import com.vinaygaba.rubberstamp.RubberStampConfig;
import com.vinaygaba.rubberstamp.RubberStampPosition;

import au.com.spatialvision.consol.watermarking.R;

/**
 * Created by gota on 8/03/18.
 */

public class Watermarker {
    private static final String LOG_TAG = "Watermarker";

    final int width;
    final int height;

    final Context context;
    private Bitmap marking;

    public Watermarker(Context context, Bitmap src) {
        Log.d(LOG_TAG, "src: " + src);
        this.marking = src;
        width = src.getWidth();
        height = src.getHeight();

        this.context = context;
    }

    Bitmap mark() {
        return mark(marking, "Watermark TOP_LEFT", RubberStampPosition.TOP_LEFT)
                .mark(marking, "Watermark TOP_RIGHT", RubberStampPosition.TOP_RIGHT)
                .mark(marking, "Watermark BOTTOM_LEFT", RubberStampPosition.BOTTOM_LEFT)
                .mark(marking, "Watermark BOTTOM_RIGHT", RubberStampPosition.BOTTOM_RIGHT)
                .markBg(marking).marking;
//        RubberStampConfig config = new RubberStampConfig.RubberStampConfigBuilder()
//                .base(src)
//                .rubberStamp("Watermark \n TOP LEFT")
//                .rubberStampPosition(RubberStampPosition.TOP_LEFT)
//                .alpha(100)
//                .margin(30, 30)
//                //.rotation(-45)
//                .textColor(Color.BLACK)
//                //.textBackgroundColor(Color.WHITE)
//                //.textShadow(0.1f, 5, 5, Color.BLUE)
//                .textSize(90)
//                //.textFont("fonts/serif.ttf")
//                .build();
//
//        RubberStamp rubberStamp = new RubberStamp(context);
//
//        return rubberStamp.addStamp(config);
    }

    private Watermarker mark(Bitmap base, String watermark, RubberStampPosition position) {
        RubberStampConfig config = new RubberStampConfig.RubberStampConfigBuilder()
                .base(base)
                .rubberStamp(watermark)
                .rubberStampPosition(position)
                .alpha(100)
                .margin(30, 30)
                //.rotation(-45)
                .textColor(Color.BLACK)
                //.textBackgroundColor(Color.WHITE)
                //.textShadow(0.1f, 5, 5, Color.BLUE)
                .textSize(90)
                //.textFont("fonts/serif.ttf")
                .build();

        RubberStamp rubberStamp = new RubberStamp(context);

        return updateMarking(rubberStamp.addStamp(config));
    }

    public Watermarker markBg(Bitmap src) {
        Log.d(LOG_TAG, "markBg: " + src );
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());

        Canvas canvas = new Canvas(result);

        canvas.drawBitmap(src, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(20);
//        paint.setTextSize(size);
//        //paint.setAntiAlias(true);
//        paint.setUnderlineText(false);
        canvas.drawRect(0, 0, 50, 50, paint);

        return updateMarking(result);
    }

    private Watermarker updateMarking(Bitmap toUpdate) {
        marking = toUpdate;
        return this;
    }
}

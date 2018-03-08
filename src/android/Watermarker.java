package com.terikon.cordova.photolibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.vinaygaba.rubberstamp.RubberStamp;
import com.vinaygaba.rubberstamp.RubberStampConfig;
import com.vinaygaba.rubberstamp.RubberStampPosition;

/**
 * Created by gota on 8/03/18.
 */

public class Watermarker {
    private static final String LOG_TAG = "Watermarker";

    final JobDetails details;
    final Context context;
    final Position position;
    private Bitmap marking;

    public Watermarker(Context context, Bitmap src, JobDetails details) {
        Log.d(LOG_TAG, "src: " + src);
        this.marking = src;
        final int width = src.getWidth();
        final int height = src.getHeight();
        position = new Position(width, height);

        this.context = context;
        this.details = details;
    }

    Bitmap mark() {
        return mark(marking, details.id, RubberStampPosition.TOP_LEFT, position.topLeftLine1)
                .mark(marking, details.address, RubberStampPosition.TOP_LEFT, position.topLeftLine2)
                .mark(marking, details.name, RubberStampPosition.TOP_LEFT, position.topLeftLine3)
                .mark(marking, details.name, RubberStampPosition.TOP_RIGHT, position.topRightLine1)
                .mark(marking, details.lat, RubberStampPosition.BOTTOM_LEFT, position.bottomLeftLine2)
                .mark(marking, details.lng, RubberStampPosition.BOTTOM_LEFT, position.bottomLeftLine1)
                .mark(marking, details.date, RubberStampPosition.BOTTOM_RIGHT, position.bottomRightLine2)
                .mark(marking, details.time, RubberStampPosition.BOTTOM_RIGHT, position.bottomRightLine1)
                .markBg(marking).marking;
    }

    private Watermarker mark(Bitmap base, String watermark, RubberStampPosition position, Margin margin) {
        RubberStampConfig config = new RubberStampConfig.RubberStampConfigBuilder()
                .base(base)
                .rubberStamp(watermark)
                .rubberStampPosition(position)
                .alpha(100)
                .margin(margin.x, margin.y)
                //.rotation(-45)
                .textColor(Color.BLACK)
                .textBackgroundColor(Color.WHITE)
                //.textShadow(0.1f, 5, 5, Color.BLUE)
                .textSize(this.position.textSize)
                //.textFont("fonts/serif.ttf")
                .build();

        RubberStamp rubberStamp = new RubberStamp(context);

        return updateMarking(rubberStamp.addStamp(config));
    }

    private Watermarker markBg(Bitmap src) {
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

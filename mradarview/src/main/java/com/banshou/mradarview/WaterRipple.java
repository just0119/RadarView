package com.banshou.mradarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by cjq on 2018/9/3.
 * Email: stoic_yb@139.com
 * features:
 */
public class WaterRipple extends View {
    private Paint mPaint;

    public WaterRipple(Context context, Paint paint) {
        super(context);
        if (paint == null) {
            this.mPaint = new Paint();
        } else {
            this.mPaint = paint;
        }
    }

    public WaterRipple(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = Math.min(canvas.getWidth(), canvas.getHeight()) / 2;
        canvas.drawCircle(radius, radius, radius, mPaint);
    }
}

package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by user on 6/9/17.
 */

public class Monster extends Entity {
    public Monster(float mx, float my, float width, float height, Context context) {
        super(mx, my, width, height, context);
    }

    public Monster(float mx, float my, float width, float height, int level, float hp, Context context) {
        super(mx, my, width, height, level, hp, context);
    }
    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        canvas.drawRect((int)mx - Data.camX, (int)my - Data.camY,(int) width  + (int) mx - Data.camX,(int)height + (int)my - Data.camY, paint);
    }
}
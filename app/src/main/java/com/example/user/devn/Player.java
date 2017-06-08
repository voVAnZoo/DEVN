package com.example.user.devn;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Player extends Entity{

    public Player(float mx, float my, int width, int height) {
        super(mx, my, width, height);
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        canvas.drawRect((int)mx - Data.camX, (int)my - Data.camY,(int) width - Data.camX,(int)height - Data.camY, paint);
        //invalidate();
    }


    public void go(float x, float y){
        addMx(x);
        addWidth(x);
        addMy(y);
        addHeight(y);

    }

    @Override
    public void action() {

    }
}

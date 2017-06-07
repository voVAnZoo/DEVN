package com.example.user.devn;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Player extends Entity{

    public void setMx(int x) {
        this.mx = x;
    }

    public void setMy(int y) {
        this.my = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        canvas.drawRect((int)mx, (int)my, width, height, paint);
        //invalidate();
    }

    @Override
    public void action() {

    }
}

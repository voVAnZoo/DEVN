package com.example.user.devn;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Player extends Entity{

    //<kostil
    float mx = 10;
    float my = 10;
    int width = 200;
    int height = 200;
    boolean isgo = false;
    //>


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

    public void addMx(int dx){
        mx += dx;
    }

    public void addMy(int dy){
        my += dy;
    }

    public void addWidth(int dWidth){
        width += dWidth;
    }

    public void addHeight(int dHeight){
        height += dHeight;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        canvas.drawRect((int)mx, (int)my, width, height, paint);
        //invalidate();
    }

    @Override
    public void action() {
       if (isgo){
           GameActivity.player.addMx(10);
           GameActivity.player.addWidth(10);
       }
    }
}

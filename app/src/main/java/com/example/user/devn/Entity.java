package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 6/6/17.
 */

public class Entity {

    float mx = 10;
    float my = 10;
    int width = 200;
    int height = 200;

    public Entity(){

    }

    public float getMx() {
        return mx;
    }

    public void setMx(float mx) {
        this.mx = mx;
    }

    public float getMy() {
        return my;
    }

    public void setMy(float my) {
        this.my = my;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
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

    void onDraw(Canvas canvas, Paint paint) {}

    public void action(){}

}

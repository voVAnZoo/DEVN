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

    float mx;
    float my;
    float width;
    float height;

    public Entity(float mx, float my, float width, float height) {
        this.mx = mx;
        this.my = my;
        this.width = width;
        this.height = height;
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

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void addMx(float dx){
        mx += dx;
    }

    public void addMy(float dy){
        my += dy;
    }

    public void addWidth(float dWidth){
        width += dWidth;
    }

    public void addHeight(float dHeight){
        height += dHeight;
    }

    void onDraw(Canvas canvas, Paint paint) {
    }

    public void action(){}

}

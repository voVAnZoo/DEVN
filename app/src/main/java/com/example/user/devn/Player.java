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
    int x = 10;
    int y = 10;
    int width = 200;
    int height = 200;
    //>

    Paint paint = new Paint();

    public Player(Context context) {
        super(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Player(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void addX(int dx){
        x += dx;
    }

    public void addY(int dy){
        y += dy;
    }

    public void addWidth(int dWidth){
        width += dWidth;
    }

    public void addHeight(int dHeight){
        height += dHeight;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(x, y, width, height, paint);
        invalidate();
    }
}

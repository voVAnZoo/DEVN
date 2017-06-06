package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 6/6/17.
 */

public class Entity extends View {


    public Entity(Context context) {
        super(context);
    }

    public Entity(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Entity(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}

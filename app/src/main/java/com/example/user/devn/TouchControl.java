package com.example.user.devn;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by user on 6/6/17.
 */

public class TouchControl implements OnTouchListener{

    float x = -1;
    float y = -1;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                    x = event.getX();
                    y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Data.camX -= event.getX() - x;
                Data.camY -= event.getY() - y;
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = -1;
                y = -1;
            case MotionEvent.ACTION_CANCEL:

                break;
        }

        return true;
    }
}
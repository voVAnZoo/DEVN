package com.example.user.devn;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by user on 6/6/17.
 */

public class TouchControl implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
@Override
public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
        }

@Override
public boolean onDoubleTap(MotionEvent e) {
        return false;
        }

@Override
public boolean onDoubleTapEvent(MotionEvent e) {
        /*/<Kostil!!!
        GameActivity.player.addX(10);
        GameActivity.player.addWidth(10);
        //>*/
        return false;
        }

@Override
public boolean onDown(MotionEvent e) {
        return false;
        }

@Override
public void onShowPress(MotionEvent e) {

        }

@Override
public boolean onSingleTapUp(MotionEvent e) {
        return false;
        }

@Override
public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
        }

@Override
public void onLongPress(MotionEvent e) {

        }

@Override
public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(Math.abs(e2.getX() - e1.getX()) > Math.abs(e2.getY() - e1.getY())){
        if(e2.getX() - e1.getX() > 0){
        GameActivity.player.addX(10);
        GameActivity.player.addWidth(10);
        }else{
        GameActivity.player.addX(-10);
        GameActivity.player.addWidth(-10);
        }
        }else{
        if(e2.getY() - e1.getY() > 0){
        GameActivity.player.addY(10);
        GameActivity.player.addHeight(10);
        }else{
        GameActivity.player.addY(-10);
        GameActivity.player.addHeight(-10);
        }
        }
        return false;
        }
}

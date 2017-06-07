package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by user on 6/6/17.
 */

public class GameMap extends View {
    public int maparr[][];

    public static Vector entity;

    public GameMap(Context context) {
        super(context);
    }

    public GameMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void init(){

        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for(int i = 0;i < entity.size();i++){
                    Entity e = (Entity) entity.get(i);
                    e.action();
                }
            }
        };
        t.schedule(timerTask,0,100);
    }

    public void addEntity(Entity e){
        entity.add(e);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0;i < entity.size();i++){
            Entity e = (Entity) entity.get(i);
            Paint paint = new Paint();
            e.onDraw(canvas,paint);
        }
    }
}

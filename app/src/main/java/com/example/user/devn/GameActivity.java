package com.example.user.devn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class GameActivity extends AppCompatActivity {


    public static Player player;
    public static Vector entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player = (Player)findViewById(R.id.player);
        entity.add(player);
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for(int i = 0;i < entity.size();i++){
                    Entity a = (Entity) entity.get(i);
                }
            }
        };
        t.schedule(timerTask,0,100);


        View rootView = findViewById(android.R.id.content);
        final GestureDetector gestureDetector = new GestureDetector(this, new TouchControl());
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }
}

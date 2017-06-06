package com.example.user.devn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

public class GameActivity extends AppCompatActivity {


    public static Player player;
    public static Vector entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player = (Player)findViewById(R.id.player);
        //entity.add(player);

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

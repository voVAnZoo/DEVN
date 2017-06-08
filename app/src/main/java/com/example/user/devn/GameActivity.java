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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View rootView = findViewById(android.R.id.content);
        rootView.setOnTouchListener(new TouchControl());

        String name  = this.getIntent().getStringExtra("continue");
        GameMap gm = (GameMap) findViewById(R.id.player);
        if(name != null) {
            gm.open(name);
        }else {
            gm.generate();
        }
    }
}

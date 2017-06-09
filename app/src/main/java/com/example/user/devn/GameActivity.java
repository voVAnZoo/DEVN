package com.example.user.devn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
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
            try {
                gm.open(name);
            }catch (IOException e){

            }
        }else {
            gm.generate();
        }

        Button aa = (Button) findViewById(R.id.pause);

        int argb = Color.argb(0,0,0,0);

        aa.setBackgroundColor(argb);
    }
}

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
        final GameMap gm = (GameMap) findViewById(R.id.player);
        if(name != null) {
            try {
                gm.open(name);
            } catch (IOException e) {

            }
        } else {
            gm.generate();
        }

        Button aa = (Button) findViewById(R.id.pause);
        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("dfdf");
            }
        });

        Button up = (Button) findViewById(R.id.up);
        Button down = (Button) findViewById(R.id.down);
        Button left = (Button) findViewById(R.id.left);
        Button right = (Button) findViewById(R.id.right);
        Button search = (Button) findViewById(R.id.search);

        final Player he = (Player) gm.entitys.get(0);

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        he.setSpeedY(Float.parseFloat("-10"));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        he.setSpeedY(Float.parseFloat("0"));
                        he.setSpeedX(Float.parseFloat("0"));
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return true;
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        he.setSpeedY(Float.parseFloat("10"));
                        System.out.println("dfdf");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        he.setSpeedY(Float.parseFloat("0"));
                        he.setSpeedX(Float.parseFloat("0"));
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return true;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        he.setSpeedX(Float.parseFloat("-10"));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        he.setSpeedY(Float.parseFloat("0"));
                        he.setSpeedX(Float.parseFloat("0"));
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return true;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        he.setSpeedX(Float.parseFloat("10"));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        he.setSpeedY(Float.parseFloat("0"));
                        he.setSpeedX(Float.parseFloat("0"));
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return true;
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}


package com.example.user.devn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

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
            start(gm);
        }

        final Intent pause = new Intent(this,PauseActivity.class);
        Button btPause = (Button) findViewById(R.id.pause);
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pause);
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
                        he.setSpeedY(10);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        he.setSpeedY(0);
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
                        he.setSpeedX(-10);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        he.setSpeedX(0);
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
                        he.setSpeedX(10);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        he.setSpeedX(0);
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
                Data.camX = (int) (he.mx + he.width / 2 - Data.sizeX / 2);
                Data.camY = (int) (he.my + he.height / 2 - Data.sizeY / 2);

                if (Data.camX < 0) {
                    Data.camX = 0;
                } else {
                    if (Data.camX > Data.mapWidth * Data.cellWidth - Data.sizeX) {
                        Data.camX = Data.mapWidth * Data.cellWidth - Data.sizeX;
                    }
                }
                if (Data.camY < 0) {
                    Data.camY = 0;
                } else {
                    if (Data.camY > Data.mapHeight * Data.cellHeight - Data.sizeY) {
                        Data.camY = Data.mapHeight * Data.cellHeight - Data.sizeY;
                    }
                }
            }
        });
    }

    public void start(GameMap gm){
        try {
            gm.generate();
        }catch (Exception e){
            start(gm);
        }
    }
}
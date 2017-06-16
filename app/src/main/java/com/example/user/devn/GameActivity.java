package com.example.user.devn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class GameActivity extends AppCompatActivity {
    static GameMap gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View rootView = findViewById(android.R.id.content);
        rootView.setOnTouchListener(new View.OnTouchListener() {

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
                        if(Data.camX - event.getX() + x < 0 ){
                            Data.camX = 0;
                        }else {
                            if(Data.camX - event.getX() + x > Data.mapWidth*Data.cellWidth - Data.sizeX){
                                Data.camX = Data.mapWidth*Data.cellWidth - Data.sizeX;
                            }else {
                                Data.camX -= event.getX() - x;
                            }
                        }

                        if(Data.camY - event.getY() + y < 0 ){
                            Data.camY = 0;
                        }else {
                            if(Data.camY - event.getY() + y > Data.mapHeight*Data.cellHeight - Data.sizeY){
                                Data.camY = Data.mapHeight*Data.cellHeight - Data.sizeY;
                            }else {
                                Data.camY -= event.getY() - y;
                            }
                        }

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
        });

        String name  = this.getIntent().getStringExtra("continue");
        gm = (GameMap) findViewById(R.id.player);
        if(name != null) {
            try {
                gm.open(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            start(gm);
        }

        final Intent pause = new Intent(this,PauseActivity.class);
        Button btPause = (Button) findViewById(R.id.pause);
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pause,1);
            }
        });

        Button up = (Button) findViewById(R.id.up);
        Button down = (Button) findViewById(R.id.down);
        Button left = (Button) findViewById(R.id.left);
        Button right = (Button) findViewById(R.id.right);
        Button search = (Button) findViewById(R.id.search);

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        gm.player.setSpeedY((float) (Data.cellHeight*-0.15));
                        gm.player.orientation = 2;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        gm.player.setSpeedY(0);
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
                        gm.player.setSpeedY((float) (Data.cellHeight*0.15));
                        gm.player.orientation = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        gm.player.setSpeedY(0);
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
                        gm.player.setSpeedX((float) (Data.cellWidth*-0.15));
                        gm.player.orientation = 1;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        gm.player.setSpeedX(0);
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
                        gm.player.setSpeedX((float) (Data.cellWidth*0.15));
                        gm.player.orientation = 3;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        gm.player.setSpeedX(0);
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
                Data.camX = (int) (gm.player.mx + gm.player.width / 2 - Data.sizeX / 2);
                Data.camY = (int) (gm.player.my + gm.player.height / 2 - Data.sizeY / 2);

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

        Button fire = (Button) findViewById(R.id.fire);
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (gm.player.orientation){
                    case 0:
                        gm.entitys.add(new FireBall(gm.player.mx + gm.player.width/2 ,gm.player.my + gm.player.height/2, Data.cellWidth /5 ,Data.cellHeight /10 ,0.0,30.0 ,gm));
                        break;
                    case 1:
                        gm.entitys.add(new FireBall(gm.player.mx + gm.player.width/2 ,gm.player.my + gm.player.height/2, Data.cellWidth /5 ,Data.cellHeight /10 ,-30.0,0.0 ,gm));
                        break;
                    case 2:
                        gm.entitys.add(new FireBall(gm.player.mx + gm.player.width/2 ,gm.player.my + gm.player.height/2, Data.cellWidth /5 ,Data.cellHeight /10 ,0.0,-30.0 ,gm));
                        break;
                    case 3:
                        gm.entitys.add(new FireBall(gm.player.mx + gm.player.width/2 ,gm.player.my+ gm.player.height/2, Data.cellWidth /5 ,Data.cellHeight /10 ,30.0,0.0 ,gm));
                        break;
                }
            }
        });
    }

    public void start(GameMap gm){
        try {
            gm.generate(1,null);
        }catch (Exception e){
            start(gm);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String name = data.getStringExtra("name");
        try {
            gm.save(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}